# 257 Binary Tree Paths

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
# @return {String[]}
def binary_tree_paths(root)
  path_info = []
  path = ""
  traversal(root, path, path_info)

  path_info
end

def traversal(root, path, path_info)
  if root.left.nil? && root.right.nil?
    path_info << path + root.val.to_s
    return
  end

  next_path = path + root.val.to_s + "->"
  traversal(root.left, next_path, path_info) if root.left
  traversal(root.right, next_path, path_info) if root.right
end

l2_1 = TreeNode.new(5)
l1_2 = TreeNode.new(3)
l1_1 = TreeNode.new(2, l2_1)
root = TreeNode.new(1, l1_1, l1_2)
p binary_tree_paths root
