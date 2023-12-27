defmodule Parallel do
    def pmap(collection, func) do
        collection
        |> Enum.map(&(Task.async(fn -> func.(&1) end)))
        |> Enum.map(&Task.await/1)
    end
end

result = Parallel.pmap(1..100, &(&1 * &1))
IO.inspect result