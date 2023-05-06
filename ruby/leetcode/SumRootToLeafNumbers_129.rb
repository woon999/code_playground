# 129 dfs Sum Root To Leaf Numbers

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
def sum_numbers(root)
  return 0 if root.nil?

  @list = []
  dfs(root, root.val.to_s)
  @list.map(&:to_i).sum
end

def dfs(root, num)
  if !root.left && !root.right
    return @list << num
  end

  dfs(root.left, num + root.left.val.to_s) if root.left
  dfs(root.right, num + root.right.val.to_s) if root.right
end

# l2_2 = TreeNode.new(1)
# l2_1 = TreeNode.new(5)
# l1_2 = TreeNode.new(0)
# l1_1 = TreeNode.new(9, l2_1, l2_2)
# root = TreeNode.new(4, l1_1, l1_2)

l1_1 = TreeNode.new(1)
root = TreeNode.new(0, l1_1)
p sum_numbers root