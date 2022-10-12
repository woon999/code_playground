# 8 ruby String to Integer

# @param {String} s
# @return {Integer}
def my_atoi(s)
  max = (2**31) -1
  min = (-2**31)

  s = s.to_i
  return s if s <= max &&  s >= min
  s < min ? min : max
end

s = "4193 with word"
p my_atoi(s)
