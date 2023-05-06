# 75 ruby Sort Colors

# @param {Integer[]} nums
# @return {Void} Do not return anything, modify nums in-place instead.
def sort_colors(nums)
  zero, one, two = 0, 0, nums.length()-1

  while(one <= two)
    if nums[one] == 0
      nums[zero], nums[one] = nums[one], nums[zero]
      zero += 1
      one += 1
    elsif nums[one] == 1
      one += 1
    elsif nums[one] == 2
      nums[one], nums[two] = nums[two], nums[one]
      two -= 1
    end
  end

  nums
end

nums = [2,0,2,1,1,0]
p sort_colors(nums)