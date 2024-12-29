# 103 BFS - Binary Tree Zigzag Level Order Traversal

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
def zigzag_level_order(root)
  return [] if root.nil?

  levels = []
  reverse = false
  q = [root]
  while !q.empty?
    nxt = []
    level = []
    q.each do |node|
      level.push node.val
      nxt << node.left if node.left
      nxt << node.right if node.right
    end
    level.reverse! if reverse

    q = nxt
    levels << level
    reverse = !reverse
  end

  levels
end

r2_2 = TreeNode.new(7)
r2_1 = TreeNode.new(15)
r1_2 = TreeNode.new(20, r2_1, r2_2)
r1_1 = TreeNode.new(9)
root = TreeNode.new(3, r1_1, r1_2)
p zigzag_level_order root
