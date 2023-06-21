# https://www.codewars.com/kata/54147087d5c2ebe4f1000805/train/elixir

defmodule Conditional do
  def _if(bool, fthen, felse) do
    if bool, do: fthen.(), else: felse.()
  end
end


defmodule TestConditional do
import Conditional, only: [_if: 3]

fthen = fn -> :then end
felse = fn -> :else end
  
  IO.puts _if(true, fthen, felse) == :then
  IO.puts _if(false, fthen, felse) == :else
end