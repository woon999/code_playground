# 102 Binary Tree Level Order Traversal

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
# @return {Integer[][]}
def level_order(root)
  res = []
  dfs(root, 0, res)

  res
end

def dfs(root, level, res)
  return if root.nil?

  res[level] ||= []
  res[level] << root.val

  dfs(root.left, level+1, res) if root.left
  dfs(root.right, level+1, res) if root.right
end

l2_2 = TreeNode.new(7)
l2_1 = TreeNode.new(15)
l1_2 = TreeNode.new(20, l2_1, l2_2)
l1_1 = TreeNode.new(9)
root = TreeNode.new(3, l1_1, l1_2)

p level_order root
