# https://www.codewars.com/kata/5467e4d82edf8bbf40000155/train/elixir

defmodule Kata do
    def descending_order(n) do
        n
        |> Integer.digits
        |> Enum.sort(&>=/2)
        |> Integer.undigits
    end
end

# Test
IO.puts Kata.descending_order(0) == 0
IO.puts Kata.descending_order(1) == 1
IO.puts Kata.descending_order(123456789)
IO.puts Kata.descending_order(123456789) == 987654321
IO.puts Kata.descending_order(145263) == 654321
IO.puts Kata.descending_order(1254859723) == 9875543221
