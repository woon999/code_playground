#include <iostream>
#include <vector>
#include <string>
#include "Scanner.cpp"
#include "Node.h"

using namespace std;
using std::string;
using std::vector;

auto parse(vector<Token>) -> Program *;
auto printTokenList(vector<Token> tokenList) -> void
{
    cout << setw(12) << left << "KIND"
         << "STRING" << endl;
    cout << string(23, '-') << endl;
    for (auto &token : tokenList)
        cout << token << endl;
}

auto main() -> int
{
    string sourceCode = R""""(
        function main() {
            p 'Hello, World!';
            pl 1+2*3;
        }
    )"""";

    cout << sourceCode << endl;
    printTokenList(scan(sourceCode));

    return 1;
}