# Interpreter

## soruce code
```
function main() {
  pl 'Hello, World!';
  pl 1+2*3;
  pl 1*2+3*4;
  pl 'abc'*3;
  
  pl true or 'Hello, World!';
  pl false or 'Hello, World!';
  pl true and 'Hello, World!';
  pl false and 'Hello, World!';
}
```

## TokenList
```
KIND        STRING
-----------------------
function    function
#identifier main
(           (
)           )
{           {
pl          pl
#String     Hello, World!
;           ;
pl          pl
#Number     1
+           +
#Number     2
*           *
#Number     3
;           ;
pl          pl
#Number     1
*           *
#Number     2
+           +
#Number     3
*           *
#Number     4
;           ;
pl          pl
#String     abc
*           *
#Number     3
;           ;
pl          pl
true        true
or          or
#String     Hello, World!
;           ;
pl          pl
false       false
or          or
#String     Hello, World!
;           ;
pl          pl
true        true
and         and
#String     Hello, World!
;           ;
pl          pl
false       false
and         and
#String     Hello, World!
;           ;
#identifier global
=           =
#Number     123123
;           ;
var         var
#identifier local
=           =
#Number     99
;           ;
pl          pl
#String     global: 
,           ,
#identifier global
;           ;
pl          pl
#String     local: 
,           ,
#identifier local
;           ;
}           }
#EndOfToken 

```

## Syntax Tree
```
FUNCTION main: 
  BLOCK:
    PRINT_LINE
      "Hello, World!"
    PRINT_LINE
      +:
        LHS:
          1
        RHS:
          *:
            LHS:
              2
            RHS:
              3
    PRINT_LINE
      +:
        LHS:
          *:
            LHS:
              1
            RHS:
              2
        RHS:
          *:
            LHS:
              3
            RHS:
              4
    PRINT_LINE
      *:
        LHS:
          "abc"
        RHS:
          3
    PRINT_LINE
      OR:
        LHS:
          true
        RHS:
          "Hello, World!"
    PRINT_LINE
      OR:
        LHS:
          false
        RHS:
          "Hello, World!"
    PRINT_LINE
      AND:
        LHS:
          true
        RHS:
          "Hello, World!"
    PRINT_LINE
      AND:
        LHS:
          false
        RHS:
          "Hello, World!"
    EXPRESSION:
      SET_VARIABLE: global
        123123
    VAR local:
      99
    PRINT_LINE
      "global: "
      GET_VARIABLE: global
    PRINT_LINE
      "local: "
      GET_VARIABLE: local
```

## result
```
Hello, World!
7
14
abcabcabc
true
Hello, World!
Hello, World!
false
global: 123123
local: 99
```


---
refs: https://github.com/AcornPublishing/crafting-compiler