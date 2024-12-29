# 21 Merge Two Sorted Lists


class ListNode
  attr_accessor :val, :next

  def initialize(val = 0, _next = nil)
    @val = val
    @next = _next
  end
end

# @param {ListNode} list1
# @param {ListNode} list2
# @return {ListNode}
def merge_two_lists(list1, list2)
  return list2 if list1.nil?
  return list1 if list2.nil?

  if list1.val < list2.val
    list1.next = merge_two_lists(list1.next, list2)
    return list1
  else
    list2.next = merge_two_lists(list1, list2.next)
    return list2
  end

  # result = ListNode.new(-1)
  # tmp = result
  # while !list1.nil? && !list2.nil?
  #   l1_val = list1.val
  #   l2_val = list2.val
  #
  #   if l1_val < l2_val
  #     tmp.next = ListNode.new(l1_val)
  #     list1 = list1.next
  #   else
  #     tmp.next = ListNode.new(l2_val)
  #     list2 = list2.next
  #   end
  #
  #   tmp = tmp.next
  # end
  #
  # unless list1.nil?
  #   tmp.next = list1
  # else
  #   tmp.next = list2
  # end
  #
  # result.next
end

l2_3 = ListNode.new(4)
l2_2 = ListNode.new(3, l2_3)
list2 = ListNode.new(1, l2_2)
l1_3 = ListNode.new(4)
l1_2 = ListNode.new(2, l1_3)
list1 = ListNode.new(1, l1_2)

r = merge_two_lists(list1, list2)
while !r.nil?
  p r.val
  r = r.next
end