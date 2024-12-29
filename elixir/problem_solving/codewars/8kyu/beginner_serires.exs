# https://www.codewars.com/kata/55f9bca8ecaa9eac7100004a/train/elixir

defmodule Kata do
  def past_(h, m, s) do
    (h * 3600 + m * 60 + s) * 1000
  end

  def past(h, m, s) do
    :timer.hours(h) + :timer.minutes(m) + :timer.seconds(s)
  end
end

# Test
IO.puts Kata.past(0,1,1) == 61000
IO.puts Kata.past(1,1,1) == 3661000
IO.puts Kata.past(0,0,0) == 0
IO.puts Kata.past(1,0,1) == 3601000
IO.puts Kata.past(1,0,0) == 3600000