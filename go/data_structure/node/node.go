package node

type Node interface {
	GetChildren() []Node
	GetValue() any
}
