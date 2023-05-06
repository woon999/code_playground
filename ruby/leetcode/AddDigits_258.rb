# 258 Add Digits

# @param {Integer} num
# @return {Integer}
def add_digits(num)
  while num >= 10
    num = num.to_s.split('').map { |val| val.to_i }.reduce(:+)
  end

  num
end

num = 38
p add_digits num