defmodule Greeter do
    def for(name, greeting) do
        fn 
            (^name) -> "#{greeting}, #{name}!"
            (_) -> "I don't know you"
        end 
    end 

    def add_one do &(&1 + 1) end
    def square do &(&1 * &1) end
    def speak do &IO.puts/1 end
end

lee = Greeter.for("lee", "Hello")
IO.puts lee.("lee") # Hello, lee!
IO.puts lee.("joe") # I don't know you


add_one = Greeter.add_one
IO.puts add_one.(1) # 2
IO.puts add_one.(44) # 45

square = Greeter.square
IO.puts square.(2) # 4
IO.puts square.(4) # 16

speak = Greeter.speak
speak.("Hello") # Hello
speak.("Goodbye") # Goodbye