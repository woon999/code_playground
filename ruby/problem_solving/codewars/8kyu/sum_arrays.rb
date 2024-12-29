# https://www.codewars.com/kata/53dc54212259ed3d4f00071c/train/ruby

def sum_(numbers)
  numbers.sum
end

def sum__(numbers)
  numbers.inject(0, :+)
end

def sum(numbers)
  numbers.reduce(0, :+)
end


p sum([]) # 0
p sum([1, 5.2, 4, 0, -1]) # 9.2
