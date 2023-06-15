# https://www.codewars.com/kata/5583090cbe83f4fd8c000051/train/ruby

def digitize_(n)
  n.to_s.split("").reverse.map(&:to_i)
end

def digitize(n)
  n.digits
end


p digitize(35231) # [1,3,2,5,3]
p digitize(0) # [0]