# 234 Palindrome Linked List

# Definition for singly-linked list.
class ListNode
    attr_accessor :val, :next
    def initialize(val = 0, _next = nil)
        @val = val
        @next = _next
    end
end

# @param {ListNode} head
# @return {Boolean}
def is_palindrome(head)
  list = []
  while head != nil
    list << head.val

    head = head.next
  end

  list == list.reverse
end

n3 = ListNode.new(1)
n2 = ListNode.new(2, n3)
n1 = ListNode.new(2, n2)
head = ListNode.new(1, n1)
p is_palindrome head