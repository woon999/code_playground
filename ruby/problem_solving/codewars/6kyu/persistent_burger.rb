# https://www.codewars.com/kata/55bf01e5a717a0d57e0000ec/train/ruby

# n.digits 39 -> [3,9]
# [3,9].reduce(:*) -> 27 ; (acc,n)
def persistence(n)
  n < 10 ? 0 : 1 + persistence(n.digits.reduce(:*))
end

p persistence(39) == 3
p persistence(4) == 0
p persistence(25) == 2
p persistence(999) == 4
p persistence(444) == 3