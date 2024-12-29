package composition

import "fmt"

type Human struct {
	FirstName string
	LastName  string
	CanSwim   bool
}

type Amy struct {
	Human // Human의 메서드 집합에 속하는 메서드를 실행할 수 있음
}

type Alan struct {
	Human // Human의 메서드 집합에 속하는 메서드를 실행할 수 있음
}

func (h *Human) Name() {
	fmt.Println("Hello! My name is %v %v", h.FirstName, h.LastName)
}

func (h *Human) Swim() {

	if h.CanSwim == true {
		fmt.Println("I can swim!")
	} else {
		fmt.Println("I can not swim.")
	}
}
