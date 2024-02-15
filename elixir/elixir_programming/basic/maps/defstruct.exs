defmodule Subscriber do
    defstruct name: "", paid: false, over_18: true
end 

# iex script 
# s1 = %Subscriber{}
# => %Subscriber{name: "", paid: false, over_18: true}
# s2 = %Subscriber{name: "Lee", paid: true, over_18: true}
# => %Subscriber{name: "Lee", paid: true, over_18: true}