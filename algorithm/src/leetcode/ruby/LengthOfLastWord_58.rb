# 58 Length of Last Word

# @param {String} s
# @return {Integer}
def length_of_last_word(s)
  s.split().last.length
end

s = "Hello   world  asd"
p length_of_last_word(s)
