package main

import (
	"fmt"
	"oop/abstraction"
	"oop/composition"
	"oop/encapsulation"
	"oop/polymorphism"
)

type SomeStruct struct {
	Field string
}

// foo는 SomeStruct의 메서드 집합에 속함
// (s *SomeStruct)는 SomeStruct 포인터에 대한 리시버이다
func (s *SomeStruct) foo(field string) {
	s.Field = field
}

func main() {
	value := SomeStruct{Field: "Structs are values"}

	// 일반 변수
	copy := value
	copy.Field = "This is a Copy & doesn't change the variable value"
	fmt.Println(value.Field) // "Structs are values"
	fmt.Println(copy.Field)  // "This is a Copy & doesn't change the variable value"

	// pointer
	value.foo("a")
	fmt.Println(value.Field) // "a"
	value.foo("b")
	fmt.Println(value.Field) // "b"

	// ----------------------------------------
	// encapsulation
	e := encapsulation.Encapsulation{}

	e.Expose()

	// e.hide() // 컴파일 에러
	e.Unhide()

	// ----------------------------------------
	// polymorphism
	hillary := new(polymorphism.Hillary)
	hillary.Slogan()                // "Stronger together."
	polymorphism.SaySlogan(hillary) // "Stronger together."

	trump := new(polymorphism.Trump)
	trump.Slogan()                // "Make America great again."
	polymorphism.SaySlogan(trump) // "Make America great again."

	// ----------------------------------------
	// composition
	// amy는 Human 타입으로 구성됨
	amy := composition.Amy{
		Human: composition.Human{
			FirstName: "Amy",
			LastName:  "Chen",
			CanSwim:   true,
		},
	}

	// alan은 Human 타입으로 구성됨
	alan := composition.Alan{
		Human: composition.Human{
			FirstName: "Alan",
			LastName:  "Chen",
			CanSwim:   false,
		},
	}

	// Human의 메서드 집합은 Amy 타입으로 전달됨
	amy.Name()  // "Hello! My name is Amy Chen"
	amy.Swim()  // "I can swim!"
	alan.Name() // "Hello! My name is Alan Chen"
	alan.Swim() // "I can't swim

	// ----------------------------------------
	// abstraction

	// Hillary와 Trump는 SloganSayer의 구현체
	hillary2 := new(abstraction.Hillary)
	trump2 := new(abstraction.Trump)

	// Campaign 타입은 SloganSayer를 임베딩함
	h := abstraction.Campaign{hillary2}
	t := abstraction.Campaign{trump2}

	h.Slogan() // "Stronger together."
	t.Slogan() // "Make America great again."

	// SloganSayer를 SaySlogan의 파라미터로 주입할 수 있음
	abstraction.SaySlogan(hillary2) // "Stronger together."
	abstraction.SaySlogan(trump2)   // "Make America great again."

	// h와 t는 또한 Campaign 타입이라는걸 기억하라
	abstraction.SaySlogan(h) // "Stronger together."
	abstraction.SaySlogan(t) // "Make America great again."
}

// ----------------------------------------
// encapsulation -> package encapsulation
// inheritance -> composition, embedding
// polymorphism -> interfaces
// abstraction -> interfaces, embedding
