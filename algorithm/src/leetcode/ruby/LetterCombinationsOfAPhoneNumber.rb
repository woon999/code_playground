# 17 Letter Combinations of a Phone Number

# @param {String} digits
# @return {String[]}
def letter_combinations(digits)
  return [] if digits.length < 1

  box = {
    '2' => ['a','b','c'],
    '3' => ['d','e','f'],
    '4' => ['g','h','i'],
    '5' => ['j','k','l'],
    '6' => ['m','n','o'],
    '7' => ['p','q','r','s'],
    '8' => ['t','u','v'],
    '9' => ['w','x','y','z']
  }

  arr = []
  arr.tap do |input|
    digits.each_char { |c| input << box[c] }
  end

  arr[0].product(*arr[1..]).map(&:join)
end

digits = "23"
p letter_combinations(digits)