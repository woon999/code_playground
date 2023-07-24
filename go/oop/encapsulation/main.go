package encapsulation

import "fmt"

// Encapsulation은 노출됨
type Encapsulation struct{}

// Expose는 노출됨
func (e *Encapsulation) Expose() {
	fmt.Println("AHHHH! I'm exposed!")
}

// 소문자로 시작하면 패키지 밖으로 노출되지 않음
func (e *Encapsulation) hide() {
	fmt.Println("Shhhh... this is super secret")
}

// Unhide는 노출됨
func (e *Encapsulation) Unhide() {
	e.hide()
	fmt.Println("...jk")
}
