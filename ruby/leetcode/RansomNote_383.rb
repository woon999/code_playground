# 383 RansomNote

# @param {String} ransom_note
# @param {String} magazine
# @return {Boolean}
def can_construct(ransom_note, magazine)
  map = Hash.new{ |h,k| h[k] = 0 }
  magazine.split('').each { |c| map[c] += 1 }

  ransom_note.split('').each do |c|
    map[c] -= 1
    return false if map[c] < 0
  end

  true
end

ransom_note = "aa"
magazine = "aab"
p can_construct ransom_note, magazine