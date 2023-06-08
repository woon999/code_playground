# https://www.codewars.com/kata/523b623152af8a30c6000027/train/elixir

defmodule SimpleMath do
  def square(n), do: n*n
end

IO.puts SimpleMath.square(0) == 0
IO.puts SimpleMath.square(1) == 1
IO.puts SimpleMath.square(2) # == 4
IO.puts SimpleMath.square(5) # == 25
IO.puts SimpleMath.square(10) # == 100
