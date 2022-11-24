# 89 Gray Code

# @param {Integer} n
# @return {Integer[]}
def gray_code(n)
  Array.new(1 << n) { |i| i ^ (i >> 1) }
end

n = 2
p gray_code n