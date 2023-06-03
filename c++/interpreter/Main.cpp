#include "Main.h"

auto main() -> int
{
    string sourceCode = R""""(
        function main() {
            pl 'Hello, World!';
            pl 1+2*3;
            pl 1*2+3*4;
            pl 'abc'*3;
        }
    )"""";

    auto tokenList = scan(sourceCode);
    auto syntaxTree = parse(tokenList);
    interpret(syntaxTree);
    return 1;
}