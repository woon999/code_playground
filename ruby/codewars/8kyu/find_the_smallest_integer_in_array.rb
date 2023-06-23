# https://www.codewars.com/kata/55a2d7ebe362935a210000b2/train/ruby

def find_smallest_int(arr)
  arr.sort.first
end

p find_smallest_int([78,56,232,12,8]) == 8
p find_smallest_int([78,56,-2,12,8]) == -2
p find_smallest_int([-78,56,-2,12,8]) == -78
p find_smallest_int([-8]) == -8
p find_smallest_int([1,2,3,4,5,6,7,8,9,10]) == 1
p find_smallest_int([-1,-2,-3,-4,-5,-6,-7,-8,-9,-10]) == -10
p find_smallest_int([-78,56,232,12,8]) == -78
p find_smallest_int([78,56,-2,12,-8]) == -8
p find_smallest_int([-8,-3]) == -8
p find_smallest_int([-3,-8]) == -8