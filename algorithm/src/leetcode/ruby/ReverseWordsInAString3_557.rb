# 557 Reverse Words in a String 3

# @param {String} s
# @return {String}
def reverse_words(s)
  s.split.map { |s| s.reverse }.join(" ")
end

s = "Let's take LeetCode contest"
p reverse_words s