#pragma once
#include "Token.h"
#include "Node.h"

auto scan(string) -> vector<Token>;
auto parse(vector<Token>) -> Program *;
auto printTokenList(vector<Token> tokenList) -> void;
auto printSyntaxTree(Program *) -> void;
auto interpret(Program *) -> void;
