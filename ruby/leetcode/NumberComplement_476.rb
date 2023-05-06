# 476 Number Complement

# @param {Integer} num
# @return {Integer}
def find_complement(num)
  complement = ~num
  size = num.to_s(2).length
  (size-1).downto(0).map { |n| complement[n] }.join.to_i(2)
end

# 101
num = 5
p find_complement num