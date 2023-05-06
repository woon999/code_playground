# 112 PathSum

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
# @param {Integer} target_sum
# @return {Boolean}
def has_path_sum(root, target_sum)
  return false if root.nil? || target_sum.nil?

  dfs(root, target_sum)
end

def dfs(root, target_sum, path_sum = 0)
  return false if root.nil?

  path_sum += root.val

  return true if path_sum == target_sum if !root.left && !root.right

  f = false
  f |= dfs(root.left, target_sum, path_sum)
  f |= dfs(root.right, target_sum, path_sum)

  f
end

l3 = TreeNode.new(3)
l2 = TreeNode.new(2)
root = TreeNode.new(1, l2, l3)

p has_path_sum(root, 5)