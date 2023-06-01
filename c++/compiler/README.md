
# 어휘 분석 
```
function main() {
    p 'Hello, World!';
    pl 1+2*3;
}
```

``` 
KIND        STRING
-----------------------
function    function
#identifier main
(           (
)           )
{           {
printL      p
#String     Hello, World!
;           ;
printL      pl
#Number     1
;           ;
}           }
#EndOfToken 
```


# 구문 분석
```
function main() {
    p 'Hello, World!';
    pl 1+2*3;
}
 ```   

 ```
 FUNCTION main: 
  BLOCK:
    PRINT:
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
 ```