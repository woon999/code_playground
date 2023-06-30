#include <any>
#include <map>
#include <list>
#include <vector>
#include <functional>
#include "Datatype.h"
#include "Code.h"
#include <cmath>

using std::any;
using std::cout;
using std::endl;
using std::function;
using std::get;
using std::list;
using std::map;
using std::tuple;
using std::vector;

// StackFrame: 함수를 실행하기 위해 필요한 공간
// 명령어 포인터: 다음에 실행할 명령어의 위치 (heap보다 빠른 이유)
// 변수: 함수 내에서 사용하는 변수들
// 피연산자 스택: 함수 내에서 사용하는 피연산자들
struct StackFrame
{
  vector<any> variables;
  vector<any> operandStack;
  size_t instructionPointer = 0;
};
static list<Object *> objects;
static map<string, any> global;
static vector<StackFrame> callStack;
extern map<string, function<any(vector<any>)>> builtinFunctionTable;

static auto pushOperand(any value) -> void;
static auto peekOperand() -> any;
static auto popOperand() -> any;
static auto collectGarbage() -> void;
static auto markObject(any) -> void;
static auto sweepObject() -> void;

// 목적 코드 실행
auto execute(tuple<vector<Code>, map<string, size_t>> objectCode) -> void
{
  global.clear();
  objects.clear();
  callStack.emplace_back();

  // 1. 실행코드 가져오기
  auto codeList = get<0>(objectCode);
  // 2. 함수 테이블 가져오기
  auto functionTable = get<1>(objectCode);
  while (true)
  {
    // 코드 명령에 따라 적절한 동작 수행
    auto code = codeList[callStack.back().instructionPointer];
    switch (code.instruction)
    {
    case Instruction::Exit: // 종료
    {
      callStack.pop_back();
      return;
    }
    case Instruction::Call:
    {
      auto operand = popOperand();
      if (isSize(operand))
      {
        StackFrame stackFrame;
        stackFrame.instructionPointer = toSize(operand);
        for (size_t i = 0; i < toSize(code.operand); i++)
        {
          stackFrame.variables.push_back(callStack.back().operandStack.back());
          callStack.back().operandStack.pop_back();
        }
        callStack.push_back(stackFrame);
        continue;
      }
      if (isBuiltinFunction(operand)) // 내장 함수 호출
      {
        vector<any> arguments;
        for (size_t i = 0; i < toSize(code.operand); i++)
          arguments.push_back(popOperand());
        pushOperand(toBuiltinFunction(operand)(arguments));
        break;
      }
      pushOperand(nullptr);
      break;
    }
    case Instruction::Alloca: // 지역 변수 메모리 할당 (스택 프레임 배열 크기 확장)
    {
      auto extraSize = toSize(code.operand);
      auto currentSize = callStack.back().variables.size();
      callStack.back().variables.resize(currentSize + extraSize);
      break;
    }
    case Instruction::Return:
    {
      any result = nullptr;
      if (callStack.back().operandStack.empty() == false)
        result = callStack.back().operandStack.back();
      callStack.pop_back();
      callStack.back().operandStack.push_back(result);
      collectGarbage(); // 함수 종료 시점에 mark-and-sweep 수행
      break;
    }
    case Instruction::Jump:
    {
      callStack.back().instructionPointer = toSize(code.operand);
      continue;
    }
    case Instruction::ConditionJump:
    {
      auto condition = popOperand();
      if (isTrue(condition))
        break;
      callStack.back().instructionPointer = toSize(code.operand);
      continue;
    }
    case Instruction::Print:
    {
      for (size_t i = 0; i < toSize(code.operand); i++)
      {
        auto value = popOperand();
        cout << value;
      }
      break;
    }
    case Instruction::PrintLine:
    {
      cout << endl;
      break;
    }
    case Instruction::LogicalOr:
    {
      auto value = popOperand();
      if (isTrue(value))
      {
        pushOperand(value);
        callStack.back().instructionPointer = toSize(code.operand);
        continue;
      }
      break;
    }
    case Instruction::LogicalAnd:
    {
      auto value = popOperand();
      if (isFalse(value))
      {
        pushOperand(value);
        callStack.back().instructionPointer = toSize(code.operand);
        continue;
      }
      break;
    }
    case Instruction::Equal:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNull(lValue) && isNull(rValue))
        pushOperand(true);
      else if (isBoolean(lValue) && isBoolean(rValue))
        pushOperand(toBoolean(lValue) == toBoolean(rValue));
      else if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) == toNumber(rValue));
      else if (isString(lValue) && isString(rValue))
        pushOperand(toString(lValue) == toString(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::NotEqual:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNull(lValue) && isNull(rValue))
        pushOperand(false);
      else if (isNull(lValue) || isNull(rValue))
        pushOperand(true);
      if (isBoolean(lValue) && isBoolean(rValue))
        pushOperand(toBoolean(lValue) != toBoolean(rValue));
      else if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) != toNumber(rValue));
      else if (isString(lValue) && isString(rValue))
        pushOperand(toString(lValue) != toString(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::LessThan:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) < toNumber(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::GreaterThan:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) > toNumber(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::LessOrEqual:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) <= toNumber(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::GreaterOrEqual:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) >= toNumber(rValue));
      else
        pushOperand(false);
      break;
    }
    case Instruction::Add:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) + toNumber(rValue));
      else if (isString(lValue) && isString(rValue))
        pushOperand(toString(lValue) + toString(rValue));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::Subtract:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) - toNumber(rValue));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::Multiply:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) * toNumber(rValue));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::Divide:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue) && toNumber(rValue) == 0)
        pushOperand(0.0);
      else if (isNumber(lValue) && isNumber(rValue))
        pushOperand(toNumber(lValue) / toNumber(rValue));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::Modulo:
    {
      auto rValue = popOperand();
      auto lValue = popOperand();
      if (isNumber(lValue) && isNumber(rValue) && toNumber(rValue) == 0)
        pushOperand(0.0);
      else if (isNumber(lValue) && isNumber(rValue))
        pushOperand(fmod(toNumber(lValue), toNumber(rValue)));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::Absolute:
    {
      auto value = popOperand();
      if (isNumber(value))
        pushOperand(abs(toNumber(value)));
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::ReverseSign:
    {
      auto value = popOperand();
      if (isNumber(value))
        pushOperand(toNumber(value) * -1);
      else
        pushOperand(0.0);
      break;
    }
    case Instruction::GetElement:
    {
      auto index = popOperand();
      auto sub = popOperand();
      if (isArray(sub) && isNumber(index))
        pushOperand(getValueOfArray(sub, index));
      else if (isMap(sub) && isString(index))
        pushOperand(getValueOfMap(sub, index));
      else
        pushOperand(nullptr);
      break;
    }
    case Instruction::SetElement:
    {
      auto index = popOperand();
      auto sub = popOperand();
      if (isArray(sub) && isNumber(index))
        setValueOfArray(sub, index, peekOperand());
      else if (isMap(sub) && isString(index))
        setValueOfMap(sub, index, peekOperand());
      break;
    }
    case Instruction::GetGlobal:
    {
      auto name = toString(code.operand);
      if (functionTable.count(name))
        pushOperand(functionTable[name]);
      else if (builtinFunctionTable.count(name))
        pushOperand(builtinFunctionTable[name]);
      else if (global.count(name))
        pushOperand(global[name]);
      else
        pushOperand(nullptr);
      break;
    }
    case Instruction::SetGlobal:
    {
      auto name = toString(code.operand);
      global[name] = peekOperand();
      break;
    }
    case Instruction::GetLocal:
    {
      auto index = toSize(code.operand);
      pushOperand(callStack.back().variables[index]);
      break;
    }
    case Instruction::SetLocal:
    {
      auto index = toSize(code.operand);
      callStack.back().variables[index] = peekOperand();
      break;
    }
    case Instruction::PushNull:
    {
      pushOperand(nullptr);
      break;
    }
    case Instruction::PushBoolean:
    {
      pushOperand(code.operand);
      break;
    }
    case Instruction::PushNumber:
    {
      pushOperand(code.operand);
      break;
    }
    case Instruction::PushString:
    {
      pushOperand(code.operand);
      break;
    }
    case Instruction::PushArray:
    {
      auto result = new Array();
      auto size = toSize(code.operand);
      for (auto i = size; i > 0; i--)
        result->values.push_back(popOperand());
      pushOperand(result);
      objects.push_back(result);
      break;
    }
    case Instruction::PushMap:
    {
      auto result = new Map();
      for (size_t i = 0; i < toSize(code.operand); i++)
      {
        auto value = popOperand();
        auto key = toString(popOperand());
        result->values[key] = value;
      }
      pushOperand(result);
      objects.push_back(result);
      break;
    }
    case Instruction::PopOperand:
    {
      popOperand();
      break;
    }
    }

    // 동작 수행 후 명령어 포인터 1 증가
    callStack.back().instructionPointer += 1;
  }
}

auto pushOperand(any value) -> void
{
  callStack.back().operandStack.push_back(value);
}

auto peekOperand() -> any
{
  return callStack.back().operandStack.back();
}

auto popOperand() -> any
{
  auto value = callStack.back().operandStack.back();
  callStack.back().operandStack.pop_back();
  return value;
}

// 가비지 컬렉션 --------------------------------------------------
auto collectGarbage() -> void
{
  for (auto &stackFrame : callStack)
  {
    for (auto &value : stackFrame.operandStack)
      markObject(value);
    for (auto &value : stackFrame.variables)
      markObject(value);
  }
  for (auto &[key, value] : global)
    markObject(value);
  sweepObject();
}

auto markObject(any value) -> void
{
  if (isArray(value))
  {
    if (toArray(value)->isMarked)
      return;
    toArray(value)->isMarked = true;
    for (auto &value : toArray(value)->values)
      markObject(value);
  }
  else if (isMap(value))
  {
    if (toMap(value)->isMarked)
      return;
    toMap(value)->isMarked = true;
    for (auto &[key, value] : toMap(value)->values)
      markObject(value);
  }
}

auto sweepObject() -> void
{
  objects.remove_if([](Object *object)
                    {
    if (object->isMarked) {
      object->isMarked = false;
      return false;
    }
    delete object;
    return true; });
}

// --------------------------------------------------
