# https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1/train/ruby

def duplicate_count(text)
  text.downcase!
  text.chars.uniq.count { |char| text.count(char) > 1 }
end

p duplicate_count("") == 0
p duplicate_count("abcde") == 0
p duplicate_count("abcdeaa") == 1
p duplicate_count("abcdeaB") == 2
p duplicate_count("Indivisibilities") == 2