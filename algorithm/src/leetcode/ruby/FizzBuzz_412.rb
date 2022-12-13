# 412 Fizz Buzz

# @param {Integer} n
# @return {String[]}
def fizz_buzz(n)
  (1..n).map do |num|
    if is_fizz(num) && is_buzz(num)
      'FizzBuzz'
    elsif is_fizz(num)
      'Fizz'
    elsif is_buzz(num)
      'Buzz'
    else
      num.to_s
    end
  end
end

def is_fizz(num)
  num % 3 == 0
end

def is_buzz(num)
  num % 5 == 0
end

n = 15
p fizz_buzz n