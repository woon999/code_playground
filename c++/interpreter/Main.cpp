#include "Main.h"

auto main() -> int
{
    string sourceCode0 = R""""(
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
            
            for i =0, i<3, i=i+1 {
                pl 'i: ', i;
            }

            for i =0, i<5, i=i+1 {
                if i == 1 {
                    pl 'one';
                } elsif i == 2{
                    pl 'two';
                } elsif i== 3 {
                    pl 'three';
                } else {
                    pl i;
                }
            }

            for i =0, i<5, i=i+1 {
                if i == 1 {
                    continue;
                }

                pl i;
            }
        }
    )"""";

    string sourceCode = R""""(
        function main() {
            sayHo();
            add(1,2);
        }

        function sayHo(){
            pl 'Ho!';
        }

        function add(a, b){
            pl a+b;
        }
    )"""";

    auto tokenList = scan(sourceCode);
    printTokenList(tokenList);

    auto syntaxTree = parse(tokenList);
    printSyntaxTree(syntaxTree);

    interpret(syntaxTree);
    return 1;
}