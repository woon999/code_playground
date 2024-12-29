defmodule SquareSum do
  def square_sum0(nums) do
    Enum.reduce(nums, 0, fn num, acc -> acc + (num * num) end)
  end

  def square_sum(nums) do
    nums
    |> Enum.map(&(&1 * &1))
    |> Enum.sum
  end
end

IO.puts (SquareSum.square_sum([1,2]) == 5)
IO.puts (SquareSum.square_sum([0, 3, 4, 5]) == 50)
IO.puts (SquareSum.square_sum([]) == 0)
IO.puts (SquareSum.square_sum([-1,-2]) == 5)
IO.puts (SquareSum.square_sum([-1,0,1]) == 2)
