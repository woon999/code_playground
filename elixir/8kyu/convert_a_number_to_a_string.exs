# https://www.codewars.com/kata/5265326f5fda8eb1160004c8/train/elixir

defmodule Stringify do
    def number_to_string(n), do: "#{n}"
    def number_to_string_(n), do: Integer.to_string(n)
end

# Test
IO.puts Stringify.number_to_string(67) == "67"
