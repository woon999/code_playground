# https://www.codewars.com/kata/515e271a311df0350d00000f/train/ruby

def square_sum(numbers)
  numbers.map { |num| num ** 2 }.sum
end

p square_sum([1, 2]) == 5
p square_sum([0, 3, 4, 5]) == 50
p square_sum([]) == 0
p square_sum([-1, -2]) == 5
p square_sum([-1, 0, 1]) == 2
