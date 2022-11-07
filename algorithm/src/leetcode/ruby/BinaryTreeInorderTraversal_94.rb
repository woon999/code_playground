# ruby 94 Binary Tree Inorder Traversal

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
def inorder_traversal(root)
  return inorder(root, [])
end

def inorder(root, list)
  return list if root.nil?

  inorder(root.left, list)
  list << root.val
  inorder(root.right, list)
end


node3 = TreeNode.new(3)
node2 = TreeNode.new(2, node3, nil)
root = TreeNode.new(1, nil, node2)
p inorder_traversal(root)