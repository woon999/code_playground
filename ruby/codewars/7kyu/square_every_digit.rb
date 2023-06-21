# https://www.codewars.com/kata/546e2562b03326a88e000020/train/ruby

def square_digits(num)
  num.to_s.chars.map{|x| x.to_i**2}.join.to_i
end

p square_digits(3212) == 9414
p square_digits(2112) == 4114
p square_digits(1111) == 1111
p square_digits(1234321) == 14916941