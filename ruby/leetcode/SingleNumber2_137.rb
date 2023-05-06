# 137 Single Number2

# @param {Integer[]} nums
# @return {Integer}
def single_number(nums)
  nums.sort!
  count = 0
  last_num = nums[0]
  nums.each do |num|
    if num == last_num
      count += 1
    elsif count < 3
      return last_num
    else
      count = 1
      last_num = num
    end
  end

  last_num
end

nums = [0,1,0,1,0,1,99]
p single_number nums