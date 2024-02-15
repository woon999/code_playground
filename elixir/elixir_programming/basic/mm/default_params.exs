defmodule Example do
  def func(a, b \\ 2, c \\ 3), do: print(a, b, c)
  defp print(a, b, c), do: IO.puts "a: #{a}, b: #{b}, c: #{c}"
end


Example.func(1, 4, 5) # a: 1, b: 4, c: 5
Example.func(1, 4)    # a: 1, b: 4, c: 3
Example.func(1)       # a: 1, b: 2, c: 3