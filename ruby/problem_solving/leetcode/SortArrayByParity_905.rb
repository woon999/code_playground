# 905 two pointer Sort Array By Parity

# @param {Integer[]} nums
# @return {Integer[]}
def sort_array_by_parity(nums)

  i, j = 0, nums.length-1

  while i < j
    if nums[i].odd? && nums[j].even?
      nums[i], nums[j] = nums[j], nums[i]
      i += 1
      j -= 1
    else
      i += 1 if nums[i].even?
      j -= 1 if nums[j].odd?
    end
  end

  nums
end

nums = [3, 1, 2, 4]
p sort_array_by_parity nums