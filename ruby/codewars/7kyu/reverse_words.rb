# 7kyu reverse words
# https://www.codewars.com/kata/5259b20d6021e9e14c0010d4/ruby

# [^ ] is a negated character class that matches any character except a space.
# By using [^ ]+, we match one or more consecutive characters that are not spaces.
def reverse_words1(str)
  str.gsub(/([^ ]+)/, &:reverse)
end

# \S	Any non-whitespace character (== !\s)
def reverse_words2(str)
  str.gsub(/(\S+)/, &:reverse)
end

p reverse_words2('The quick brown fox jumps over the lazy dog.') == 'ehT kciuq nworb xof spmuj revo eht yzal .god'
p reverse_words2('apple') == 'elppa'
p reverse_words2('a b c d') == 'a b c d'
p reverse_words2('double  spaced  words') == 'elbuod  decaps  sdrow'
