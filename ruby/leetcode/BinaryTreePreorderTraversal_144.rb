# ruby 144 Binary Tree Preorder Traversal

# Definition for a binary tree node.
class TreeNode
    attr_accessor :val, :left, :right
    def initialize(val = 0, left = nil, right = nil)
        @val = val
        @left = left
        @right = right
    end
end

# @param {TreeNode} root
# @return {Integer[]}
def preorder_traversal(root)
  return preorder(root, [])
end

def preorder(root, list)
  return list if root.nil?

  list << root.val
  preorder(root.left, list)
  preorder(root.right, list)
end


node3 = TreeNode.new(3)
node2 = TreeNode.new(2, node3, nil)
root = TreeNode.new(1, nil, node2)
p preorder_traversal(root)