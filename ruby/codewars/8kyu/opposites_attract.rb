def lovefunc( flower1, flower2 )
  !(flower1.odd? ^ flower2.even?)
end

puts lovefunc(1,4) == true
puts lovefunc(2,2) == false
puts lovefunc(0,1) == true
puts lovefunc(0,0) == false
puts lovefunc(5,5) == false