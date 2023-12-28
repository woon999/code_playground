defmodule Spawn4 do
    def greet do
        receive do
            {sender, msg} ->
                send sender, {:ok, "Hello, #{msg}!"}
                greet() # recursive call
        end
    end         
end


pid = spawn(Spawn4, :greet, [])
send pid, {self(), "World!"}

receive do
    {:ok, msg} ->
        IO.puts msg
end

# now it's work!
send pid, {self(), "Karma!"}
receive do
    {:ok, msg} ->
        IO.puts msg
    after 500 ->
        IO.puts "Timeout!"
end