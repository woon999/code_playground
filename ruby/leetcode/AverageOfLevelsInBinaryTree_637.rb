# 637 Average of Levels in Binary Tree

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
# @return {Float[]}
def average_of_levels(root)
  box = Hash.new { |h, k| h[k] = [] }

  dfs(root, 0, box)

  box.values.map { |arr| arr.sum.to_f/arr.size }
end

def dfs(root, depth, box)
  box[depth] << root.val if root.val

  dfs(root.left, depth+1, box) if root.left
  dfs(root.right, depth+1, box) if root.right
end

l2_2 = TreeNode.new(7)
l2_1 = TreeNode.new(15)
l1_2 = TreeNode.new(20, l2_1, l2_2)
l1_1 = TreeNode.new(9)
root = TreeNode.new(3, l1_1, l1_2)

p average_of_levels root