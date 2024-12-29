# https://www.codewars.com/kata/57eae65a4321032ce000002d/train/ruby

def fake_bin(s)
  s.chars.map { |n| n.to_i < 5 ? 0 : 1 }.join
end

p fake_bin('45385593107843568') # '01011110001100111')
p fake_bin('509321967506747') # '101000111101101'
p fake_bin('366058562030849490134388085') # '011011110000101010000011011'