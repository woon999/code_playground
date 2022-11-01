# 49 Group Anagram

# @param {String[]} strs
# @return {String[][]}
def group_anagrams(strs)

  map = Hash.new { |h, k| h[k] = [] }
  strs.each do |str|
    key = str.chars.sort.join(',')

    map[key] << str
  end

  map.values
end

strs = ["eat","tea","tan","ate","nat","bat"]
p group_anagrams(strs)