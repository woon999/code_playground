# ruby 9 Palindrome Number

# @param {Integer} x
# @return {Boolean}
def is_palindrome(x)
  x = x.to_s

  x.eql? x.reverse
end


x = -121
p is_palindrome(x)