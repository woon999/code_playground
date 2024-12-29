// function ----------------------------------------------------
// type은 후위에 선언해야 한다.
function sum(x: number, y: number): number {
    return x + y;
}
console.log(sum(1, 2));

let myAdd: (x: number, y: number) => number =
    function (x: number, y: number): number { return x + y; };

// 타입 추론으로 인해 생략 가능
let myAdd2: (baseValue: number, increment: number) => number =
    function (x, y) { return x + y; };

console.log(myAdd(1, 2));
console.log(myAdd2(1, 2));

// Optional and Default Parameter)
function buildName(firstName: string, lastName = "Smith") {
    return firstName + " " + lastName;
}

let result1 = buildName("Bob");                  // 올바르게 동작, "Bob Smith" 반환
let result2 = buildName("Bob", undefined);       // 여전히 동작, 역시 "Bob Smith" 반환
// let result3 = buildName("Bob", "Adams", "Sr.");  // 오류, 너무 많은 매개변수
let result4 = buildName("Bob", "Adams");         // 정확함
console.log(result1);
console.log(result2);
console.log(result4);

// Rest Parameters
function buildName2(firstName: string, ...restOfName: string[]) {
    return firstName + " " + restOfName.join(" ");
}
let employeeName = buildName2("Joseph", "Samuel", "Lucas", "MacKinzie"); // good. "Joseph Samuel Lucas MacKinzie"
console.log(employeeName);

function doSomething(): void {
    console.log('I void.');
}

function throwError(message: string): never {
    throw new Error(message);
}


