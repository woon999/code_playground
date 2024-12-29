# 415 Add Strings

# @param {String} num1
# @param {String} num2
# @return {String}
def add_strings(num1, num2)
  (num1.to_i + num2.to_i).to_s
end

num1 = "11"
num2 = "123"
p add_strings(num1, num2)