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
~~~
function main()
~~~

function    function
#identifier main
(           (
)           )
{           {

~~~
pl 'Hello, World!';
pl 1+2*3;
pl 1*2+3*4;
pl 'abc'*3;
~~~

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

~~~
pl true or 'Hello, World!';
pl false or 'Hello, World!';
pl true and 'Hello, World!';
pl false and 'Hello, World!';
~~~

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

~~~
global = 123123;
var local = 99;
pl 'global: ', global;
pl 'local: ', local;
~~~

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

~~~
global = local = 1;
pl 'global: ', global;
pl 'local: ', local;
~~~

#identifier global
=           =
#identifier local
=           =
#Number     1
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

~~~
for i =0, i<3, i=i+1 {
  pl 'i: ', i;
}
~~~

for         for
#identifier i
=           =
#Number     0
,           ,
#identifier i
<           <
#Number     3
,           ,
#identifier i
=           =
#identifier i
+           +
#Number     1
{           {
pl          pl
#String     i: 
,           ,
#identifier i
;           ;
}           }

~~~
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
~~~

for         for
#identifier i
=           =
#Number     0
,           ,
#identifier i
<           <
#Number     5
,           ,
#identifier i
=           =
#identifier i
+           +
#Number     1
{           {
if          if
#identifier i
==          ==
#Number     1
{           {
pl          pl
#String     one
;           ;
}           }
elsif       elsif
#identifier i
==          ==
#Number     2
{           {
pl          pl
#String     two
;           ;
}           }
elsif       elsif
#identifier i
==          ==
#Number     3
{           {
pl          pl
#String     three
;           ;
}           }
else        else
{           {
pl          pl
#identifier i
;           ;
}           }
}           }

~~~
for i =0, i<5, i=i+1 {
    if i == 1 {
        continue;
    }
    pl i;
}
~~~

for         for
#identifier i
=           =
#Number     0
,           ,
#identifier i
<           <
#Number     5
,           ,
#identifier i
=           =
#identifier i
+           +
#Number     1
{           {
if          if
#identifier i
==          ==
#Number     1
{           {
continue    continue
;           ;
}           }
pl          pl
#identifier i
;           ;
}           }

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
    EXPRESSION:
      SET_VARIABLE: global
        SET_VARIABLE: local
          1
    PRINT_LINE
      "global: "
      GET_VARIABLE: global
    PRINT_LINE
      "local: "
      GET_VARIABLE: local
    FOR:
      VARIABLE:
        VAR i:
          0
      CONDITION:
        <:
          LHS:
            GET_VARIABLE: i
          RHS:
            3
      EXPRESSION:
        SET_VARIABLE: i
          +:
            LHS:
              GET_VARIABLE: i
            RHS:
              1
      BLOCK:
        PRINT_LINE
          "i: "
          GET_VARIABLE: i
    FOR:
      VARIABLE:
        VAR i:
          0
      CONDITION:
        <:
          LHS:
            GET_VARIABLE: i
          RHS:
            5
      EXPRESSION:
        SET_VARIABLE: i
          +:
            LHS:
              GET_VARIABLE: i
            RHS:
              1
      BLOCK:
        IF:
          CONDITION:
            ==:
              LHS:
                GET_VARIABLE: i
              RHS:
                1
          BLOCK:
            PRINT_LINE
              "one"
        ELSEIF:
          CONDITION:
            ==:
              LHS:
                GET_VARIABLE: i
              RHS:
                2
          BLOCK:
            PRINT_LINE
              "two"
        ELSEIF:
          CONDITION:
            ==:
              LHS:
                GET_VARIABLE: i
              RHS:
                3
          BLOCK:
            PRINT_LINE
              "three"
        ELSE:
          PRINT_LINE
            GET_VARIABLE: i
    FOR:
      VARIABLE:
        VAR i:
          0
      CONDITION:
        <:
          LHS:
            GET_VARIABLE: i
          RHS:
            5
      EXPRESSION:
        SET_VARIABLE: i
          +:
            LHS:
              GET_VARIABLE: i
            RHS:
              1
      BLOCK:
        IF:
          CONDITION:
            ==:
              LHS:
                GET_VARIABLE: i
              RHS:
                1
          BLOCK:
            CONTINUE
        PRINT_LINE
          GET_VARIABLE: i
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
global: 1
local: 1
i: 0
i: 1
i: 2
0
one
two
three
4
0
2
3
4
```

---

## funcation SoruceCode
```
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
```

## Token List
```
KIND        STRING
-----------------------
function    function
#identifier main
(           (
)           )
{           {
#identifier sayHo
(           (
)           )
;           ;
#identifier add
(           (
#Number     1
,           ,
#Number     2
)           )
;           ;
}           }

~~~
function sayHo(){
    pl 'Ho!';
}
~~~

function    function
#identifier sayHo
(           (
)           )
{           {
pl          pl
#String     Ho!
;           ;
}           }
function    function


~~~
function add(a, b){
  pl a+b;
}
~~~

#identifier add
(           (
#identifier a
,           ,
#identifier b
)           )
{           {
pl          pl
#identifier a
+           +
#identifier b
;           ;
}           }

~~~
function getC(a, b){
  return a * a + b * b;
}
~~~

#identifier getC
(           (
#identifier a
,           ,
#identifier b
)           )
{           {
return      return
#identifier a
*           *
#identifier a
+           +
#identifier b
*           *
#identifier b
;           ;
}           }

#EndOfToken 
```

## Syntax Tree
```
FUNCTION main: 
  BLOCK:
    EXPRESSION:
      CALL:
        EXPRESSION:
          GET_VARIABLE: sayHo
    EXPRESSION:
      CALL:
        EXPRESSION:
          GET_VARIABLE: add
        ARGUMENT:
          1
        ARGUMENT:
          2
FUNCTION sayHo: 
  BLOCK:
    PRINT_LINE
      "Ho!"

FUNCTION add: 
  PARAMETERS:a b 
  BLOCK:
    PRINT_LINE
      +:
        LHS:
          GET_VARIABLE: a
        RHS:
          GET_VARIABLE: b

FUNCTION getC: 
  PARAMETERS:a b 
  BLOCK:
    RETURN:
      +:
        LHS:
          *:
            LHS:
              GET_VARIABLE: a
            RHS:
              GET_VARIABLE: a
        RHS:
          *:
            LHS:
              GET_VARIABLE: b
            RHS:
              GET_VARIABLE: b
```

## result
```
Ho!
3
25
```


---
refs: https://github.com/AcornPublishing/crafting-compiler