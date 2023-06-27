#pragma once
#include <any>
#include <map>
#include <vector>
#include <string>
#include "Token.h"

using std::any;
using std::map;
using std::string;
using std::vector;

struct Program
{
    vector<struct Function *> functions;
};

struct Statement
{
    virtual auto generate() -> void = 0;
    virtual auto print(int) -> void = 0;
};

struct Expression
{
    virtual auto generate() -> void = 0;
    virtual auto print(int) -> void = 0;
};

struct Function : Statement
{
    string name;
    vector<string> parameters;
    vector<Statement *> block;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Variable : Statement
{
    string name;
    Expression *expression;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Return : Statement
{
    Expression *expression;
    auto generate() -> void;
    auto print(int) -> void;
};

struct For : Statement
{
    Variable *variable;
    Expression *condition;
    Expression *expression;
    vector<Statement *> block;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Break : Statement
{
    auto generate() -> void;
    auto print(int) -> void;
};

struct Continue : Statement
{
    auto generate() -> void;
    auto print(int) -> void;
};

struct If : Statement
{
    vector<Expression *> conditions;
    vector<vector<Statement *>> blocks;
    vector<Statement *> elseBlock;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Print : Statement
{
    bool lineFeed = false;
    vector<Expression *> arguments;
    auto generate() -> void;
    auto print(int) -> void;
};

struct ExpressionStatement : Statement
{
    Expression *expression;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Or : Expression
{
    Expression *lhs;
    Expression *rhs;
    auto generate() -> void;
    auto print(int) -> void;
};

struct And : Expression
{
    Expression *lhs;
    Expression *rhs;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Relational : Expression
{
    Kind kind;
    Expression *lhs;
    Expression *rhs;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Arithmetic : Expression
{
    Kind kind;
    Expression *lhs;
    Expression *rhs;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Unary : Expression
{
    Kind kind;
    Expression *sub;
    auto generate() -> void;
    auto print(int) -> void;
};

struct Call : Expression
{
    Expression *sub;
    vector<Expression *> arguments;
    auto generate() -> void;
    auto print(int) -> void;
};

struct GetElement : Expression
{
    Expression *sub;
    Expression *index;
    auto generate() -> void;
    auto print(int) -> void;
};

struct SetElement : Expression
{
    Expression *sub;
    Expression *index;
    Expression *value;
    auto generate() -> void;
    auto print(int) -> void;
};

struct GetVariable : Expression
{
    string name;
    auto generate() -> void;
    auto print(int) -> void;
};

struct SetVariable : Expression
{
    string name;
    Expression *value;
    auto generate() -> void;
    auto print(int) -> void;
};

struct NullLiteral : Expression
{
    auto generate() -> void;
    auto print(int) -> void;
};

struct BooleanLiteral : Expression
{
    bool value = false;
    auto generate() -> void;
    auto print(int) -> void;
};

struct NumberLiteral : Expression
{
    double value = 0.0;
    auto generate() -> void;
    auto print(int) -> void;
};

struct StringLiteral : Expression
{
    string value;
    auto generate() -> void;
    auto print(int) -> void;
};

struct ArrayLiteral : Expression
{
    vector<Expression *> values;
    auto generate() -> void;
    auto print(int) -> void;
};

struct MapLiteral : Expression
{
    map<string, Expression *> values;
    auto generate() -> void;
    auto print(int) -> void;
};
