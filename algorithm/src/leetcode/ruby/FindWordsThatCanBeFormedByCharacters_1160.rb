# 1160 Find Words That Can Be Formed by Characters

# @param {String[]} words
# @param {String} chars
# @return {Integer}
def count_characters(words, chars)
  res = 0
  char_map = Hash.new(0)
  chars.chars.each { |c| char_map[c] += 1 }

  words.each do |word|
    word_char_map = char_map.clone

    flag = true
    word.chars.each do |c|
      word_char_map[c] -= 1
      flag = false if word_char_map[c].negative?
    end

    res += word.size if flag
  end

  res
end


words = ["cat", "bt", "hat", "tree"]
chars = "atach"
p count_characters words, chars