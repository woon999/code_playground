defmodule MyList do
    def len([]), do: 0
    def len([_head | tail]), do: 1 + len(tail)

    def sum([]), do: 0
    def sum([head | tail]), do: head + sum(tail)

    def map([], _func), do: []
    def map([head | tail], func), do: [func.(head) | map(tail, func)]

    def reduce([], value, _func), do: value
    def reduce([head | tail], value, func), do: reduce(tail, func.(head, value), func)
end

IO.puts MyList.len([])
IO.puts MyList.len([1,2,3,4,5]) # 5
IO.puts MyList.len([1,2,3,4,5,6,7,8,9,10]) # 10

IO.puts MyList.sum([])
IO.puts MyList.sum([1,2,3,4,5]) # 15
IO.puts MyList.sum([1,2,3,4,5,6,7,8,9,10]) # 55

IO.inspect MyList.map([1,2,3,4,5], fn x -> x * x end) # [1, 4, 9, 16, 25]
IO.inspect MyList.map([1,2,3,4,5], fn x -> x > 2 end) # [false, false, true, true, true]

IO.puts MyList.reduce([1,2,3,4,5], 0, fn x, acc -> x + acc end) # 15
IO.puts MyList.reduce([1,2,3,4,5], 1, fn x, acc -> x * acc end) # 120