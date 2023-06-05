# https://www.codewars.com/kata/51f2d1cafc9c0f745c00037d/train/ruby

def solution(str, ending)
  ending == str.slice(str.size-ending.size..-1)
end


p solution('abcde', 'cde') # true
p solution('abcde', 'abc') # false
p solution('abcde', '') # true