package polymorphism

import "fmt"

type SloganSayer interface {
	Slogan()
}

// SaySlogan은 SloganSayer 인터페이스를 만족하는 모든 타입을 인자로 받을 수 있음
func SaySlogan(sloganSayer SloganSayer) {
	sloganSayer.Slogan()
}

// Hillary는 Slogan 함수를 구현함으로써 암묵적으로 SloganSayer 인터페이스를 만족
type Hillary struct{}

func (h *Hillary) Slogan() {
	fmt.Println("Stronger together.")
}

// Trump도 Slogan 함수를 구현함으로써 암묵적으로 SloganSayer 인터페이스를 만족
type Trump struct{}

func (t *Trump) Slogan() {
	fmt.Println("Make America great again.")
}
