#pragma once
#include <any>
#include <string>
#include <iostream>

using std::any;
using std::ostream;
using std::string;

enum class Instruction
{
    // 프로그램 종료
    Exit,

    // 함수 호출
    Call,

    // 메모리 할당
    Alloca,

    // 함수 종료
    Return,

    // 분기
    Jump,

    // 조건 분기
    ConditionJump,

    // 콘솔 출력
    Print,
    PrintLine,

    // 논리 연산
    LogicalOr,
    LogicalAnd,

    // 산술 연산
    Add,
    Subtract,
    Multiply,
    Divide,
    Modulo,

    // 비교 연산
    Equal,
    NotEqual,
    LessThan,
    GreaterThan,
    LessOrEqual,
    GreaterOrEqual,

    // 단항 연산
    Absolute,
    ReverseSign,

    // 원소 연산
    GetElement,
    SetElement,

    // 전역 변수 연산
    GetGlobal,
    SetGlobal,

    // 지역 변수 연산
    GetLocal,
    SetLocal,

    // 프리미티브 타입 리터럴 연산
    PushNull,
    PushBoolean,
    PushNumber,
    PushString,

    // 레퍼런스 타입 리터럴 연산
    PushArray,
    PushMap,

    // 피연산자 스택 연산
    PopOperand,
};

auto toString(Instruction) -> string;

struct Code
{
    Instruction instruction;
    any operand;
};

auto operator<<(ostream &, Code &) -> ostream &;
