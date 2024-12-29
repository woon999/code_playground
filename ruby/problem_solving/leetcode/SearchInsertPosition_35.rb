# 35 search insert position

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer}
def search_insert(nums, target)
  l, r = 0, nums.length-1

  while l <= r
    mid = (l+r) >> 1
    if target == nums[mid]
      return mid
    elsif target < nums[mid]
      r = mid -1;
    else
      l = mid +1;
    end
  end

  return r+1 if l > r
  return 1
end


nums = [1,3,5,6]
target = 7

p search_insert(nums, target)