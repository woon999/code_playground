# 5 Longest Palindromic Substring

# @param {String} s
# @return {String}

def longest_palindrome(s)
  @idx, @len = 0, 0
  return s if s.length == 1

  (0...s.length).each do |i|
    search(s, i, i)
    search(s, i, i+1)
  end

  s[@idx...@idx+@len]
end

def search(s, i, j)
  while (i >= 0 && j < s.length() && s[i] == s[j]) do
    i-= 1
    j+= 1
  end

  if @len < j - i - 1
    @idx = i + 1
    @len = j - i - 1
  end
end

s = "cbbd"
p longest_palindrome(s)