#include "Main.h"

auto main() -> int
{
    string sourceCode = R""""(
        function main() {
            p 'Hello, World!';
            pl 1+2*3;
        }
    )"""";

    cout << sourceCode << endl;

    auto tokenList = scan(sourceCode);
    auto syntaxTree = parse(tokenList);
    printSyntaxTree(syntaxTree);

    return 1;
}