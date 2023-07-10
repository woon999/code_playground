# https://www.codewars.com/kata/555a67db74814aa4ee0001b5/train/elixir

defmodule Evenator do
  def even?(n) do
    # if is_float(n) do
    #     false
    # else
    #     rem(n, 2) == 0
    # end
    !is_float(n) && rem(n, 2) == 0
  end
end

# Test
IO.puts Evenator.even?(0) == true
IO.puts Evenator.even?(1) == false
IO.puts Evenator.even?(2) == true
IO.puts Evenator.even?(3) == false
IO.puts Evenator.even?(4) == true
IO.puts Evenator.even?(0.5) == false
IO.puts Evenator.even?(-4) == true