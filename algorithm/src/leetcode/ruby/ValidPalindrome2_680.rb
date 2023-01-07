# 680 Valid Palindrome 2

# @param {String} s
# @return {Boolean}
def valid_palindrome(s)
  return true if s == s.reverse
  0.upto(s.size/2).each do |i|
    if s[i] != s[s.size-1-i]
      tmp, tmp2 = s, s.reverse
      tmp[i], tmp2[i] = "", ""
      if tmp == tmp.reverse or tmp2 == tmp2.reverse
        return true
      else
        return false
      end
    end
  end
end

s = "cbbcc"
p valid_palindrome s