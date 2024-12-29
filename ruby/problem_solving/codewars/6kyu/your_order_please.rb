# https://www.codewars.com/kata/55c45be3b2079eccff00010f/train/ruby

def order(words)
  words.split.sort_by { |word| word[/\d/] }.join(' ')
end

p order("is2 Thi1s T4est 3a") # == "Thi1s is2 3a T4est"
