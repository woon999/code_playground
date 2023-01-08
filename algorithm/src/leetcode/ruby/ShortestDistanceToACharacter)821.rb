# 821 Shortest Distance to a Character

# @param {String} s
# @param {Character} c
# @return {Integer[]}
def shortest_to_char(s, c)
  last_c = nil
  result = [0] * s.chars.size
  stack = []
  s.chars.each_with_index do |char, idx|
    if char != c
      stack << idx
    else
      while !stack.empty?
        last_c = idx if last_c.nil?
        x = stack.pop
        result[x] = [idx-x, (last_c - x).abs].min
      end
      result[idx] = 0
      last_c = idx
    end
  end

  while !stack.empty?
    x = stack.pop
    result[x] = (last_c - x).abs
  end

  result
end

s = "loveleetcode"
c = "e"
p shortest_to_char s, c