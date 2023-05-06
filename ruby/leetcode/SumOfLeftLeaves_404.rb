# 404 Sum of Left Leaves

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
# @return {Integer}
def sum_of_left_leaves(root)
  left_leaves = []

  dfs(root, 0, left_leaves)

  left_leaves.sum
end

def dfs(root, type, left_leaves)
  left_leaves << root.val if type == -1 && !root.left && !root.right

  dfs(root.left, -1, left_leaves) if root.left
  dfs(root.right, 1, left_leaves) if root.right
end

l2_2 = TreeNode.new(7)
l2_1 = TreeNode.new(15)
l1_2 = TreeNode.new(20, l2_1, l2_2)
l1_1 = TreeNode.new(9)
root = TreeNode.new(3, l1_1, l1_2)

p sum_of_left_leaves root