package abstraction

import "fmt"

type SloganSayer interface {
	Slogan()
}

// SaySlogan은 파라미터로 Campaign 또한 받을 수 있음
func SaySlogan(s SloganSayer) {
	s.Slogan()
}

// 임베딩하는 방식은 체이닝시 유용
type Campaign struct {
	SloganSayer // SloganSayer 인터페이스를 구현하는 모든 타입을 포함
}

// Hillary는 SloganSayer 인터페이스를 구현함
type Hillary struct{}

func (h *Hillary) Slogan() {
	fmt.Println("Stronger together.")
}

// Trump는 SloganSayer 인터페이스를 구현함
type Trump struct{}

func (t *Trump) Slogan() {
	fmt.Println("Make American great again.")
}
