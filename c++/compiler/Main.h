#pragma once
#include <iostream>
#include <vector>
#include <string>
#include "Node.h"
#include "Token.h"

using namespace std;
using std::string;
using std::vector;

auto printSyntaxTree(Program *) -> void;
auto scan(string) -> vector<Token>;
auto parse(vector<Token>) -> Program *;