# https://www.codewars.com/kata/55b42574ff091733d900002f/train/ruby

# regex, grep
def friend(friends)
  friends.grep /^\w{4}$/
end

def my_friend(friends)
  friends.select { |f| f.length == 4 }
end


p friend(["Ryan", "Kieran", "Mark"]) # ["Ryan", "Mark"]
p friend(["Ryan", "Jimmy", "123", "4", "Cool Man"]) # ["Ryan"])
p friend(["Jimm", "Cari", "aret", "truehdnviegkwgvke", "sixtyiscooooool"]) # ["Jimm", "Cari", "aret"]
p friend(["Love", "Your", "Face", "1"]) # ["Love", "Your", "Face"]