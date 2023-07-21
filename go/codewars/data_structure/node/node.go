package node

type Node interface {
	GetChilds() []Node
	GetValue() any
}
