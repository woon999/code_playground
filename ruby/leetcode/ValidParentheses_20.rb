# 20 Valid Parentheses

# @param {String} s
# @return {Boolean}
def is_valid(s)
  stack = []
  s.each_char do |c|
    case c
    when '(', '{', '['
      stack.push(c)
    when ')'
      return false unless stack.pop == '('
    when '}'
      return false unless stack.pop == '{'
    when ']'
      return false unless stack.pop == '['
    end
  end

  return stack.empty?
end

s = "(){}[{}]"
p is_valid(s)