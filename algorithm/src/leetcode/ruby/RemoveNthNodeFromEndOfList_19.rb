# 19 Remove Nth Node From End of List

class ListNode
  attr_accessor :val, :next
  def initialize(val = 0, _next = nil)
    @val = val
    @next = _next
  end
end

# @param {ListNode} head
# @param {Integer} n
# @return {ListNode}
def remove_nth_from_end(head, n)
  pointer = head
  block = head

  while n > 0
    block = block.next
    return head.next if block.nil?
    n -= 1
  end

  while !block.next.nil?
    block = block.next
    pointer = pointer.next
  end

  # pointer.next 삭제
  pointer.next = pointer.next.next

  head
end


h5 = ListNode.new(5)
h4 = ListNode.new(4, h5)
h3 = ListNode.new(3, h4)
h2 = ListNode.new(2, h3)
h1 = ListNode.new(1, h2)

n = 2
p remove_nth_from_end(h1, n)