# 589 N-ary Tree Preorder Traversal

# Definition for a Node.
class Node
    attr_accessor :val, :children
    def initialize(val)
        @val = val
        @children = []
    end
end

# @param {Node} root
# @return {List[int]}
def preorder(root)
  return [] if root.nil?
  res = [root.val]
  root.children.each do |child|
    res += preorder(child)
  end
  res
end


root = Node.new(1)
root.children = [Node.new(3), Node.new(2), Node.new(4)]
root.children[0].children = [Node.new(5), Node.new(6)]
p preorder root