#include "Main.h"

auto main() -> int
{
    string sourceCode = R""""(
        function main() {
            pl 'Hello, World!';
            pl 1+2*3;
            pl 1*2+3*4;
            pl 'abc'*3;
            
            pl true or 'Hello, World!';
            pl false or 'Hello, World!';
            pl true and 'Hello, World!';
            pl false and 'Hello, World!';

            global = 123123;
            var local = 99;
            pl 'global: ', global;
            pl 'local: ', local;

            global = local = 1;
            pl 'global: ', global;
            pl 'local: ', local;
        }
    )"""";

    auto tokenList = scan(sourceCode);
    printTokenList(tokenList);

    auto syntaxTree = parse(tokenList);
    printSyntaxTree(syntaxTree);

    interpret(syntaxTree);
    return 1;
}