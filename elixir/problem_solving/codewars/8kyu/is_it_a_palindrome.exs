# https://www.codewars.com/kata/57f780909f7e8e3183000078/train/elixir

defmodule IsPalindrome do
  def is_palindrome(s), do: String.downcase(s) |> is_palindrome_helper()

  defp is_palindrome_helper(s), do: s == String.reverse(s)


  def testing(str, exp) do
    a = is_palindrome(str)
    if a == exp, do: "Given string #{str}, expected #{exp}, got #{a}"
  end
end


IO.puts (IsPalindrome.testing("aba", true))
IO.puts (IsPalindrome.testing("Abba", true))
IO.puts (IsPalindrome.testing("malam", true))
IO.puts (IsPalindrome.testing("walter", false))
IO.puts (IsPalindrome.testing("kodok", true))
IO.puts (IsPalindrome.testing("Kasue", false))
IO.puts (IsPalindrome.testing("hello", false))
IO.puts (IsPalindrome.testing("Bob", true))
IO.puts (IsPalindrome.testing("Madam", true))
IO.puts (IsPalindrome.testing("AbBa", true))
IO.puts (IsPalindrome.testing("", true))
IO.puts (IsPalindrome.testing("LAGrALLyiclOaEowNvmU", false))
IO.puts (IsPalindrome.testing("cWalaIYFGucHEhbnEH", false))
IO.puts (IsPalindrome.testing("smlWLKQYCrRUcqOLYuGGuYLOqcURrCYQKLWlms", true))
IO.puts (IsPalindrome.testing("dRjLeHcvvcHeLjRd", true))
IO.puts (IsPalindrome.testing("wvvqHAfrFWkIYygRQHTR", false))
IO.puts (IsPalindrome.testing("zuKWoAyeQNvhurRlYlUUlYlRruhvNQeyAoWKuz", true))
IO.puts (IsPalindrome.testing("QtFpQMSYPMnnMPYSMQpFtQ", true))
IO.puts (IsPalindrome.testing("muNcdggdcNum", true))
IO.puts (IsPalindrome.testing("TUkKinLuE", false))
IO.puts (IsPalindrome.testing("MaKeRSDisini", false))
