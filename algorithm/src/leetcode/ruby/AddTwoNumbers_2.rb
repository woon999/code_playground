# Definition for singly-linked list.
class ListNode
    attr_accessor :val, :next
    def initialize(val = 0, _next = nil)
        @val = val
        @next = _next
    end
end

# @param {ListNode} l1
# @param {ListNode} l2
# @return {ListNode}
def add_two_numbers(l1, l2)
    flag, head, tmp = 0, nil, nil
    while(l1 != nil || l2 != nil) do
        sum = flag
        sum, l1 = sum+l1.val, l1.next unless l1.nil?
        sum, l2= sum+l2.val, l2.next unless l2.nil?

        flag = 0

        sumNode = ListNode.new(sum%10)
        if head.nil?
            head, tmp = sumNode, sumNode
        else
            tmp.next, tmp = sumNode, sumNode
        end

        flag = 1 if sum >= 10
    end

    tmp.next = ListNode.new(1) if flag == 1

    head
end


l1_3 = ListNode.new(3)
l1_2 = ListNode.new(4, l1_3)
l1 = ListNode.new(2, l1_2)

l2_3 = ListNode.new(4)
l2_2 = ListNode.new(6, l2_3)
l2 = ListNode.new(5, l2_2)

p add_two_numbers(l1, l2)