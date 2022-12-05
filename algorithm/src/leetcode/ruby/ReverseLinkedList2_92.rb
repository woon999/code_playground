# 92 Reverse LinkedList2

# Definition for singly-linked list.
class ListNode
    attr_accessor :val, :next
    def initialize(val = 0, _next = nil)
        @val = val
        @next = _next
    end
end

# @param {ListNode} head
# @param {Integer} left
# @param {Integer} right
# @return {ListNode}
def reverse_between(head, left, right)
  tmp = head
  for i in 0...left-1
    tmp = tmp.next
  end

  box = []
  ans = tmp
  for i in 0..right-left
    box.push(ans.val)
    ans = ans.next
  end

  for i in 0..right-left
    tmp.val = box.pop
    tmp = tmp.next
  end

  head
end

l5 = ListNode.new(5)
l4 = ListNode.new(4, l5)
l3 = ListNode.new(3, l4)
l2 = ListNode.new(2, l3)
head = ListNode.new(1, l2)
left = 2
right = 4
p reverse_between head, left, right