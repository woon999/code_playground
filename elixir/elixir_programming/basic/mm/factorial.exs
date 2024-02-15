defmodule Factorial do
    def fact(0), do: 1
    
    # wrong! if n is negative, infinite recursion
    # def fact(n), do: n * fact(n-1)
    
    def fact(n) when is_integer(0), do: n * fact(n-1)
end

IO.puts Factorial.fact(5)
IO.puts Factorial.fact(0)
IO.puts Factorial.fact(1)
