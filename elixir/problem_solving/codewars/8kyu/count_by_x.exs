# https://www.codewars.com/kata/5513795bd3fafb56c200049e/train/elixir

defmodule Count do
    # ref best_practice
    def count_by(x, n) when x > 0 and n > 0 do
        1..n |> Enum.map(fn(i) -> x * i end)
    end

    def m_count_by(x, n) do
        Enum.map(1..n, fn(i) -> x * i end)
    end
end

IO.inspect Count.count_by(0,-2)
IO.inspect Count.count_by(1,5) # [1,2,3,4,5]
IO.inspect Count.count_by(2,5) # [2,4,6,8,10]