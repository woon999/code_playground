# 1002 Find Common Characters

# @param {String[]} words
# @return {String[]}
def common_chars(words)
  words.map(&:chars).reduce(&:&).map do |char|
    [char] * words.map { |word| word.count char }.min
  end.flatten
end

words = ["bella","label","roller"]
p common_chars words