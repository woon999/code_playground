#pragma once
#include <tuple>
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include "Token.h"
#include "Node.h"
#include "Code.h"

using std::cout;
using std::endl;
using std::get;
using std::left;
using std::right;
using std::setw;
using std::string;
using std::tuple;

auto scan(string) -> vector<Token>;
auto parse(vector<Token>) -> Program *;
auto generate(Program *) -> tuple<vector<Code>, map<string, size_t>>;
auto printObjectCode(tuple<vector<Code>, map<string, size_t>>) -> void;
