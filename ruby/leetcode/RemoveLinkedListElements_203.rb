# 203 remove linked list elements

# Definition for singly-linked list.
class ListNode
    attr_accessor :val, :next
    def initialize(val = 0, _next = nil)
        @val = val
        @next = _next
    end
end
# @param {ListNode} head
# @param {Integer} val
# @return {ListNode}
def remove_elements(head, val)
  case
  when head.nil? then return nil
  else
    case head.val
    when val then head = remove_elements(head.next, val)
    else
      head.next = remove_elements(head.next, val)
    end
  end

  head
end

p3 = ListNode.new(7)
p2 = ListNode.new(7, p3)
p1 = ListNode.new(7, p2)
head = ListNode.new(7, p1)

p remove_elements(head, 7)