# 338 Counting Bits

# @param {Integer} num
# @return {Integer[]}
def count_bits(num)
  arr = [0]
  1.upto(num) do |i|
    arr<<i.to_s(2).count('1')
  end

  arr
end


num = 5
p count_bits num