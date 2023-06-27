#include <map>
#include <list>
#include <tuple>
#include <vector>
#include <iomanip>
#include "Node.h"
#include "Code.h"

using std::cout;
using std::endl;
using std::list;
using std::map;
using std::max;
using std::setw;
using std::tuple;
using std::vector;

static auto getLocal(string) -> size_t;
static auto setLocal(string) -> void;
static auto initBlock() -> void;
static auto pushBlock() -> void;
static auto popBlock() -> void;
static auto writeCode(Instruction) -> size_t;
static auto writeCode(Instruction, any) -> size_t;
static auto patchAddress(size_t) -> void;
static auto patchOperand(size_t, size_t) -> void;

static vector<Code> codeList;
static map<string, size_t> functionTable;
static list<map<string, size_t>> symbolStack; // symbolStack에는 함수의 지역 변수와 매개 변수가 저장된다.
static vector<size_t> offsetStack;            // offsetStack에는 함수의 지역 변수와 매개 변수의 오프셋이 저장된다.
static size_t localSize;
static vector<vector<size_t>> continueStack;
static vector<vector<size_t>> breakStack;

auto generate(Program *program) -> tuple<vector<Code>, map<string, size_t>>
{
  codeList.clear();
  functionTable.clear();

  // main 함수 호출 코드 생성
  writeCode(Instruction::GetGlobal, string("main"));

  writeCode(Instruction::Call, static_cast<size_t>(0));
  writeCode(Instruction::Exit);

  // 함수 내용을 목적 코드로 생성하도록 generate() 함수에 함수 노드 리스트 순회하는 코드 추가
  for (auto &node : program->functions)
    node->generate();

  return {codeList, functionTable};
}

// Function: 함수의 내용이 목적 코드로 생성. 첫 번째로 생성하는 코드의 주소(codeList.size())가 함수의 주소가 된다.
auto Function::generate() -> void
{
  functionTable[name] = codeList.size();

  auto temp = writeCode(Instruction::Alloca);

  initBlock();

  for (auto &name : parameters)
    setLocal(name);

  for (auto &node : block)
    node->generate();

  popBlock();

  patchOperand(temp, localSize);

  // 함수 마지막에 항상 return 명령어 추가
  writeCode(Instruction::Return);
}

auto Variable::generate() -> void
{
  setLocal(name);

  expression->generate();

  writeCode(Instruction::SetLocal, getLocal(name));
  writeCode(Instruction::PopOperand);
}

auto For::generate() -> void
{
  breakStack.emplace_back();
  continueStack.emplace_back();

  pushBlock();

  variable->generate();

  auto jumpAddress = codeList.size();

  condition->generate();

  auto conditionJump = writeCode(Instruction::ConditionJump);

  for (auto &node : block)
    node->generate();

  auto continueAddress = codeList.size();
  expression->generate();

  writeCode(Instruction::PopOperand);
  writeCode(Instruction::Jump, jumpAddress);

  patchAddress(conditionJump);
  popBlock();

  // continue
  for (auto &jump : continueStack.back())
    patchOperand(jump, continueAddress);

  continueStack.pop_back();

  // break
  for (auto &jump : breakStack.back())
    patchAddress(jump);

  breakStack.pop_back();
}

auto Continue::generate() -> void
{
  if (continueStack.empty())
    return;
  auto jumpCode = writeCode(Instruction::Jump);
  continueStack.back().push_back(jumpCode);
}

auto Break::generate() -> void
{
  if (breakStack.empty())
    return;
  auto jumpCode = writeCode(Instruction::Jump);
  breakStack.back().push_back(jumpCode);
}

// if문: 여러 개의 조건식과 본문을 가진다. 하나의 본문을 실행했다면 if문 끝으로 이동.

auto If::generate() -> void
{

  // 그런데 목적 코드 작성하는 중에는 끝 주소를 알 수 없으므로 생성한 점프 명령 코드들을 보관할 리스트를 생성하고 조건식의 개수만큼 반복함으로써 시작한다.
  vector<size_t> jumpList;
  for (size_t i = 0; i < conditions.size(); i++)
  {
    conditions[i]->generate();

    auto conditionJump = writeCode(Instruction::ConditionJump);

    pushBlock();
    for (auto &node : blocks[i])
      node->generate();
    popBlock();

    jumpList.push_back(writeCode(Instruction::Jump));

    patchAddress(conditionJump);
  }

  if (elseBlock.empty() == false)
  {
    pushBlock();
    for (auto &node : elseBlock)
      node->generate();
    popBlock();
  }

  for (auto &jump : jumpList)
    patchAddress(jump);
}

// Print: 우선 인자 식 노드들을 순회해 식의 목적 코드를 생성
auto Print::generate() -> void
{
  for (auto i = arguments.size(); i > 0; i--)
    arguments[i - 1]->generate();
  writeCode(Instruction::Print, arguments.size());
  if (lineFeed)
    writeCode(Instruction::PrintLine);
}

auto Return::generate() -> void
{
  expression->generate();
  writeCode(Instruction::Return);
}

auto ExpressionStatement::generate() -> void
{
  expression->generate();
  writeCode(Instruction::PopOperand);
}

auto Or::generate() -> void
{
  lhs->generate();
  auto logicalOr = writeCode(Instruction::LogicalOr);
  rhs->generate();
  patchAddress(logicalOr);
}

auto And::generate() -> void
{
  lhs->generate();
  auto logicalAnd = writeCode(Instruction::LogicalAnd);
  rhs->generate();
  patchAddress(logicalAnd);
}

auto Relational::generate() -> void
{
  map<Kind, Instruction> instructions = {
      {Kind::Equal, Instruction::Equal},
      {Kind::NotEqual, Instruction::NotEqual},
      {Kind::LessThan, Instruction::LessThan},
      {Kind::GreaterThan, Instruction::GreaterThan},
      {Kind::LessOrEqual, Instruction::LessOrEqual},
      {Kind::GreaterOrEqual, Instruction::GreaterOrEqual}};
  lhs->generate();
  rhs->generate();
  writeCode(instructions[kind]);
}

// Arithmetic: 연산자 종류에 따라 왼쪽 식 노드와 오른쪽 식 노드를 차례로 순회하고 산술 연산 명령 목적 코드 생성
auto Arithmetic::generate() -> void
{
  map<Kind, Instruction> instructions = {
      {Kind::Add, Instruction::Add},
      {Kind::Subtract, Instruction::Subtract},
      {Kind::Multiply, Instruction::Multiply},
      {Kind::Divide, Instruction::Divide},
      {Kind::Modulo, Instruction::Modulo},
  };
  lhs->generate();
  rhs->generate();
  writeCode(instructions[kind]);
}

auto Unary::generate() -> void
{
  map<Kind, Instruction> instructions = {
      {Kind::Add, Instruction::Absolute},
      {Kind::Subtract, Instruction::ReverseSign}};
  sub->generate();
  writeCode(instructions[kind]);
}

auto Call::generate() -> void
{
  for (auto i = arguments.size(); i > 0; i--)
    arguments[i - 1]->generate();
  sub->generate();
  writeCode(Instruction::Call, arguments.size());
}

auto GetElement::generate() -> void
{
  sub->generate();
  index->generate();
  writeCode(Instruction::GetElement);
}

auto SetElement::generate() -> void
{
  value->generate();
  sub->generate();
  index->generate();
  writeCode(Instruction::SetElement);
}

auto NullLiteral::generate() -> void
{
  writeCode(Instruction::PushNull);
}

auto BooleanLiteral::generate() -> void
{
  writeCode(Instruction::PushBoolean, value);
}

auto NumberLiteral::generate() -> void
{
  writeCode(Instruction::PushNumber, value);
}

auto StringLiteral::generate() -> void
{
  writeCode(Instruction::PushString, value);
}

auto ArrayLiteral::generate() -> void
{
  for (auto i = values.size(); i > 0; i--)
    values[i - 1]->generate();
  writeCode(Instruction::PushArray, values.size());
}

auto MapLiteral::generate() -> void
{
  for (auto &[key, value] : values)
  {
    writeCode(Instruction::PushString, key);
    value->generate();
  }
  writeCode(Instruction::PushMap, values.size());
}

auto GetVariable::generate() -> void
{
  if (getLocal(name) == SIZE_MAX)
    writeCode(Instruction::GetGlobal, name);
  else
    writeCode(Instruction::GetLocal, getLocal(name));
}

auto SetVariable::generate() -> void
{
  value->generate();
  if (getLocal(name) == SIZE_MAX)
    writeCode(Instruction::SetGlobal, name);
  else
    writeCode(Instruction::SetLocal, getLocal(name));
}

auto getLocal(string name) -> size_t
{
  for (auto &symbolTable : symbolStack)
  {
    if (symbolTable.count(name))
      return symbolTable[name];
  }
  return SIZE_MAX;
}

auto setLocal(string name) -> void
{
  symbolStack.front()[name] = offsetStack.back();
  offsetStack.back() += 1;
  localSize = max(localSize, offsetStack.back());
}

auto initBlock() -> void
{
  localSize = 0;
  offsetStack.push_back(0);
  symbolStack.emplace_front();
}

auto pushBlock() -> void
{
  symbolStack.emplace_front();
  offsetStack.push_back(offsetStack.back());
}

// offsetStack, symbolStack에서 생성된 블럭을 제거한다
auto popBlock() -> void
{
  offsetStack.pop_back();
  symbolStack.pop_front();
}

// writeCode: 매개변수로 받은 명령어(instruction)와 인자(operand)를 코드 리스트에 추가하고, 추가한 코드의 인덱스를 반환한다.
auto writeCode(Instruction instruction) -> size_t
{
  codeList.push_back({instruction});
  return codeList.size() - 1;
}

auto writeCode(Instruction instruction, any operand) -> size_t
{
  codeList.push_back({instruction, operand});
  return codeList.size() - 1;
}

auto patchAddress(size_t codeIndex) -> void
{
  codeList[codeIndex].operand = codeList.size();
}

auto patchOperand(size_t codeIndex, size_t operand) -> void
{
  codeList[codeIndex].operand = operand;
}
