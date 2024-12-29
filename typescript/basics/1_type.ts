// type ----------------------------------------------------
let arr1: number[] = [1, 2, 3];
let arr2: Array<number> = [1, 2, 3]; // 제네릭 형태 선언 
let arr3: [string, number] = ['hi', 10];
let str: any = 'hi';
let num: any = 10;

const authorName: string = '안희종';
const toReaders: string = `
책을 읽어주셔서 감사합니다.
도움이 되었으면 좋겠습니다.
`;


const user: {
    readonly name: string;
    height: number;
    age?: number;
} = { name: '안희종', height: 176 };
// user.name = '종희안'; // error TS2540: Cannot assign to 'name' because it is a constant or a read-only property.

function returnVoid(): void {
    console.log('returnVoid');
}

function returnNever(): never {
    throw new Error('never');
}

function add(x: number, y: number): number {
    return x + y;
}


const nullValue: null = null;
const undefinedValue: undefined = undefined;
// const numberValue: number = null; // TS2322: Type 'null' is not assignable to type 'number'
