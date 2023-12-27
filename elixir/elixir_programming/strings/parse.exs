defmodule Parse do
    def number([ ?- | tail ]), do: _number_digits(tail, 0) * -1
    def number([ ?+ | tail ]), do: _number_digits(tail, 0)
    def number(str), do: _number_digits(str, 0)

    defp _number_digits([], val), do: val
    defp _number_digits([ digit | tail ], val) when digit in ?0..?9 do
        _number_digits(tail, val * 10 + digit - ?0)
    end
    defp _number_digits([ non_digit | _ ], _) do
        raise "Invalid digit: #{inspect non_digit}"
    end
end

IO.puts Parse.number('123')
IO.puts Parse.number('-123')
IO.puts Parse.number('+123')
IO.puts Parse.number('+9')
IO.puts Parse.number('23ab') # error