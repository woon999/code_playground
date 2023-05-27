defmodule HelloWorld do
  def greet, do: "hello world!"
end

IO.puts (HelloWorld.greet == "hello world!")
