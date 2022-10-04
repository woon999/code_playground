# 3 ruby length_of_longest_substring
# @param {String} s
# @return {Integer}
def length_of_longest_substring(s)
  stack = []
  max = 0
  s.each_char do |c|
    unless index = stack.index(c)
      p "? #{stack}"
      stack.push(c)
    else
      stack.push(c)
      (index+1).times {stack.shift}
    end

    max = [stack.length, max].max
  end

  max
end

a = [1,2,3,4,5]
3.times {a.shift}
p "dd: #{a}"

s = "abcabcbb"
p length_of_longest_substring(s)