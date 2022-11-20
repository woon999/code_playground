# 100 Same Tree

# Definition for a binary tree node.
class TreeNode
    attr_accessor :val, :left, :right
    def initialize(val = 0, left = nil, right = nil)
        @val = val
        @left = left
        @right = right
    end
end
# @param {TreeNode} p
# @param {TreeNode} q
# @return {Boolean}
def is_same_tree(p, q)
  return true if p.nil? && q.nil?
  return false if p.nil? || q.nil?

  return is_same_tree(p.left, q.left) && is_same_tree(p.right, q.right) if p.val == q.val

  false
end


q_2 = TreeNode.new(3)
q_1 = TreeNode.new(2)
q = TreeNode.new(1, q_1, q_2)

p_2 = TreeNode.new(3)
p_1 = TreeNode.new(2)
p = TreeNode.new(1, p_1, p_2)
p is_same_tree p, q