# Typescript 톺아보기 
타입스크립트은 js + 정적 타입형 + interface이다. 

<br>

# type 
1. Boolean
2. Number
3. String
4. Object
5. Array
6. Tuple
7. Enum
8. any
9. void
10. null
11. undefined
12. never


## array vs tuple
- array: 길이 동적 배열
- tuple: 길이 고정 배열 
```typescript 
// array: 타입 선언은 아래의 2가지 형태로 가능. 길이 동적 배열 
let arr: number[] = [1,2,3];
let arr: Array<number> = [1,2,3]; // 제네릭 형태 선언 

// tuple: 배열의 길이가 고정되고 각 요소의 타입이 지정되어 있는 배열 형식
let arr: [string, number] = ['hi', 10];
```

## any
모든 타입 허용. 자바스크립트를 위한 타입같음. 
```typescript
let str: any = 'hi';
let num: any = 10;
```

## never
신기한 타입이다. 함수의 끝에 절대 도달하지 않는다는 의미를 가짐. 
```typescript
```
1) 절대 반환되지 않는 함수
```typescript
function throwError(message: string): never {
    throw new Error(message);
}
```

2) 완전성 검사 
```typescript
type Shape = 'circle' | 'square' | 'triangle';

function getArea(s: Shape): number {
    switch(s) {
        case 'circle': return 3.14 * 2 * 2; // example for circle with radius 2
        case 'square': return 4 * 4;       // example for square with side length 4
        case 'triangle': return 0.5 * 3 * 4; // example for triangle with base 3 and height 4
    }
    let unexpectedShape: never = s; 
    // If there's a case we missed, TypeScript will give a compile-time error here
}
```

## 객체
특별한 건 없고 readonly 속성은 좋다. setter 불가능하게 막아준다. ?은 optional
```typescript
const user: {
    readonly name: string;
    height: number;
    age?: number;
} = { name: '안희종', height: 176 };
// user.name = '종희안'; // error TS2540: Cannot assign to 'name' because it is a constant or a read-only property.
```

## 유니온 타입
타입을 여러 개 가질 수 있다. 함수 리턴할 때 null또는 number이런식으로 하는 듯. 그리 세련되어 보이지는 않음
```typescript
type SquaredType = string | number;
function square(value: number, returnOnString: boolean = false): SquaredType {
  // ... 
}
```

## 함수 타이핑
자바스크립트 함수 타이핑 예시
```javascript
fucntion add(x, y) {
  return x + y;
}

let myAdd = function(x, y) { return x + y };
```

타입스크립트 함수 타이핑 예시 
```typescript
function add(x: number, y: number): number {
    return x + y;
}

let myAdd: (x: number, y: number) => number =
    function(x: number, y: number): number { return x + y; };
```

<br>

# 인터페이스 
```typscript
interface Shape {
    name?: string;
    getArea(): number;
}

class Circle implements Shape {
    radius: number; // 멤버 변수 radius

    constructor(radius: number) {
        this.radius = radius;
    }

    // Shape interface 메서드 구현 (안하면 에러)
    getArea() {
        return this.radius * this.radius * Math.PI;
    }
}
```

<br>

## tsconfig.json
`tst --init`하면 생성된다. 타입스크립트가 컴파일 될 때 필요한 옵션을 지정한다. 
- target: 컴파일된 코드가 어떤 환경에서 실행될 지 정의. ex) 화살표 함수를 사용하고 target 을 es5 로 한다면 일반 - - function 키워드를 사용하는 함수로 변환을 해준다. 하지만 이를 es6 로 설정한다면 화살표 함수를 그대로 유지해준다. 
- module: 컴파일된 코드가 어떤 모듈 시스템을 사용할지 정의한다. 예를 이 값을 common 으로 하면 export default Sample 을 하게 됐을 때 컴파일 된 코드에서는 exports.default = helloWorld 로 변환해주지만 이 값을 es2015 로 하면 export default Sample 을 그대로 유지하게 된다.
- strict: 모든 타입 체킹 옵션을 활성화한다는 것을 의미한다. 
- esModuleInterop: commonjs 모듈 형태로 이루어진 파일을 es2015 모듈 형태로 불러올 수 있게 해준다. 
- outDir: 컴파일 파일 저장 경로를 지정할 수 있다. 

`tsc`: 타입 스크립트 파일을 js로 컴파일 한다. 

