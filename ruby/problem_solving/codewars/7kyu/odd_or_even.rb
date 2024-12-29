# https://www.codewars.com/kata/5949481f86420f59480000e7/train/ruby

def odd_or_even(array)
  array.sum.even? ? "even" : "odd"
end

p odd_or_even([0]) == "even"
p odd_or_even([1]) == "odd"
p odd_or_even([]) == "even"
p odd_or_even([-1023, 1, -2]) == "even"
p odd_or_even([-1023, -1, 3]) == "odd"