# 3 two pointer 3Sum

# @param {Integer[]} nums
# @return {Integer[][]}
def three_sum(nums)
  res = []
  nums.sort!
  (0...nums.size).each do |i|
    num = nums[i]
    break if num.positive?

    if i.zero? || (i > 0 && num != nums[i - 1])
      lo = i + 1
      hi = nums.size - 1
      while lo < hi do
        if (num + nums[lo] + nums[hi]).zero?
          res << [num, nums[lo], nums[hi]]
          lo += 1 while lo < hi && nums[lo] == nums[lo + 1]
          hi -= 1 while lo < hi && nums[hi] == nums[hi - 1]
          lo += 1
          hi -= 1
        else
          num + nums[lo] + nums[hi] < 0 ? lo += 1 : hi -= 1
        end
      end
    end
  end
  res
end

nums = [-1,0,1,2,-1,-4]
p three_sum(nums)