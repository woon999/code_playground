defmodule WithScope do
  def run do
    content = "Now is the time"

    lp =
      with {:ok, file} <- File.open("sample.txt"),
           content <- IO.read(file, :all),
           :ok <- File.close(file),
           [_, uid, gid] <- Regex.run(~r/^_lp:.*?:(\d+):(\d+)/m, content) do
        "uid: #{uid}, gid: #{gid}"
      else
        {:error, reason} -> "Error opening file: #{reason}"
        :eof -> "File read error"
        _ -> "Pattern match failed"
      end

    IO.puts(lp)
    IO.puts(content)
  end
end

WithScope.run()
