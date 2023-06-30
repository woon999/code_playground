
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

 # 코드 생성
 ```
function main() {
    p 'Hello, World!';
    pl 1+2*3;

    for i=0, i<3, i=i+1 {
      pl i;
    }

    if true {
      p 'if';
    } elif false {
      p 'elif';
    } else {
      p 'else';
    }
}
```

```
    
FUNCTION   ADDRESS
------------------
main       3

ADDR INSTRUCTION    OPERAND
------------------------------------
   0 GetGlobal      "main"
   1 Call           [0]
   2 Exit           
   3 Alloca         [0]
   4 PushString     "Hello, World!"
   5 Print          [1]
   6 PushNumber     1
   7 PushNumber     2
   8 PushNumber     3
   9 Multiply       
  10 Add            
  11 Print          [1]
  12 PrintLine      
  
  // for문 초기화식
  13 PushNumber     0
  14 SetLocal       [0]
  15 PopOperand  

  // for문 조건식
  16 GetLocal       [0]
  17 PushNumber     3
  18 LessThan       
  
  19 ConditionJump  [29]

  // 본문 
  20 GetLocal       [0]
  21 Print          [1]
  22 PrintLine      

  // 증감식 
  23 GetLocal       [0]
  24 PushNumber     1
  25 Add            
  26 SetLocal       [0]
  27 PopOperand     
  28 Jump           [16]

  // if절의 조건식 
  29 PushBoolean    true
  30 ConditionJump  [34]

  // if절의 본문
  31 PushString     "if"
  32 Print          [1]

  33 Jump           [41]

  // elif절의 조건식 
  34 PushBoolean    false
  35 ConditionJump  [39]

  // elif절의 본문
  36 PushString     "elif"
  37 Print          [1]
  38 Jump           [41]

  // else절의 본문
  39 PushString     "else"
  40 Print          [1]

  41 Return   
 ```

# 출력 결과
```
Hello, World!7
0
1
2
if
```

 ---
 refs: https://github.com/AcornPublishing/crafting-compiler