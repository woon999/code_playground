#include "Main.h"

auto main() -> int
{
    string sourceCode = R""""(
        function main() {
            p 'Hello, World!';
            pl 1+2*3;
        }
    )"""";

    auto tokenList = scan(sourceCode);
    auto syntaxTree = parse(tokenList);
    interpret(syntaxTree);
    return 1;
}