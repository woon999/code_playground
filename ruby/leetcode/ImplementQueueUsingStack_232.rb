# 232 Implement Queue Using Stack

class MyQueue
  def initialize()
    @tmp = []
  end

=begin
    :type x: Integer
    :rtype: Void
=end
  def push(x)
    @tmp << x
  end

=begin
    :rtype: Integer
=end
  def pop()
    @tmp.shift
  end

=begin
    :rtype: Integer
=end
  def peek()
    @tmp.first
  end

=begin
    :rtype: Boolean
=end
  def empty()
    @tmp.empty?
  end
end

# Your MyQueue object will be instantiated and called as such:
obj = MyQueue.new()
obj.push(3)
param_2 = obj.pop()
param_3 = obj.peek()
param_4 = obj.empty()