// interface ----------------------------------------------------
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

class Rectangle implements Shape {
    width: number;
    height: number;
    constructor(width: number, height: number) {
        this.width = width;
        this.height = height;
    }

    // Shape interface 메서드 구현 (안하면 에러)
    getArea() {
        return this.width * this.height;
    }
}

const shapes: Shape[] = [new Circle(5), new Rectangle(10, 5)]; // interface Shape[] 타입
shapes.forEach(shape => {
    console.log(shape.getArea());
});

// type struct ----------------------------------------------------
type Person = {
    name: string;
    age?: number; // 물음표가 들어갔다는 것은, 설정을 해도 되고 안해도 되는 값이라는 것을 의미합니다.
};

// & 는 Intersection 으로서 두개 이상의 타입들을 합쳐준다 (https://www.typescriptlang.org/docs/handbook/advanced-types.html#intersection-types)
type Developer = Person & {
    skills: string[];
};

type SuperDeveloper = Developer & {
    superSkills: string[];
};

const person: Person = {
    name: '김사람'
};

const expert: Developer = {
    name: '김개발',
    skills: ['javascript', 'react']
};

const superExpert: SuperDeveloper = {
    name: '김슈퍼개발',
    skills: ['javascript', 'react'],
    superSkills: ['typescript', 'vue']
};

type People = Person[]; // Person[] 를 이제 앞으로 People 이라는 타입으로 사용 할 수 있습니다.
const people: People = [person, expert, superExpert];
people.forEach(person => console.log(person.name));

type Color = 'red' | 'orange' | 'yellow';
const color: Color = 'red';
const colors: Color[] = ['red', 'orange'];

console.log('b' + 'a' + +'a' + 'a');