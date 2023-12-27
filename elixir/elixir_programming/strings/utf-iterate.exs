defmodule Utf8 do
    def each(str, func) when is_binary(str), do: _each(str, func)

    defp _each(<< head :: utf8, tail :: binary >>, func) do
        func.(head)
        _each(tail, func)
    end

    defp _each(<<>>, _func), do: []
end

Utf8.each("hello", fn x -> IO.puts x end)
# 104
# 101
# 108
# 108
# 111