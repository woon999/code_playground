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
}           }
#EndOfToken 
```

## Syntax Tree
```
UNCTION main: 
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
```


---
refs: https://github.com/AcornPublishing/crafting-compiler