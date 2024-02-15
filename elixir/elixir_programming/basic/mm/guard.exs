defmodule Guard do
    def what_is(x) when is_number(x) do
        "#{x} is a number"
    end

    def what_is(x) when is_list(x) do
        "#{x} is a list"
    end
end

IO.puts Guard.what_is(1)
IO.puts Guard.what_is([1,2,3])
