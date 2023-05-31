#include <iostream>
#include <vector>
#include <string>
#include "Scanner.cpp"
// #include "Token.h"

using namespace std;
using std::string;
using std::vector;

auto scan(string) -> vector<Token>;
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
            printL 'Hello, World!';
            printL 1+2*3;
        }
    )"""";

    cout << sourceCode << endl;
    printTokenList(scan(sourceCode));

    return 1;
}