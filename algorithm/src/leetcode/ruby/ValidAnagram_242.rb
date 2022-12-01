# 242 Valid Anagram

# @param {String} s
# @param {String} t
# @return {Boolean}
def is_anagram(s, t)
  s.split("").sort == t.split("").sort
end