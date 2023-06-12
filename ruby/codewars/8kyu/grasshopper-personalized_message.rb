# https://www.codewars.com/kata/5772da22b89313a4d50012f7/train/ruby

def greet(name,owner)
  name == owner ? "Hello boss" : "Hello guest"
end

p greet("Daniel","Daniel") # "Hello boss"
p greet("Daniel","Jonh") # "Hello guest"