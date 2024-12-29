# ruby 10 Regular Expression Matching

# @param {String} s
# @param {String} p
# @return {Boolean}
def is_match(s, p)
  s =~ /(#{p})/
  s == $1
end

s = "ab"
p = ".*"
p is_match(s, p)