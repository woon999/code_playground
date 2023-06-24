# https://www.codewars.com/kata/5583090cbe83f4fd8c000051/train/elixir

defmodule Digitizer do
  def digitize(n) do
    n
    |> Integer.digits()
    |> Enum.reverse()
  end
end


IO.puts Digitizer.digitize(35231) == [1,3,2,5,3]
IO.puts Digitizer.digitize(0) == [0]

IO.inspect Digitizer.digitize(35231)
IO.inspect Digitizer.digitize(0)