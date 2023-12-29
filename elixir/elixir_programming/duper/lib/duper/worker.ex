defmodule Duper.Worker do
    use GenServer, restart: :transient # 일시적 실행 (서버 정상 종료시 재시작 x)

    def start_link(_) do
        GenServer.start_link(__MODULE__, :no_args)
    end

    def init(:no_args) do
        Process.send_after(self(), :do_one_file, 0)
        {:ok, nil}
    end

    def handle_info(:do_one_file, _) do
        Duper.PathFinder.next_path()
        |> add_result()
    end

    def add_result(nil) do
        Duper.Gatherer.done()
        {:stop, :normal, nil}
    end

    def add_result(path) do
        Duper.Gatherer.result(path, hash_of_file_at(path))
        send(self(), :do_one_file)
        {:noreply, nil}
    end

    def hash_of_file_at(path) do
        File.stream!(path, [], 1024*1024)
        |> Enum.reduce(:crypto.hash_init(:md5), fn (block, hash) -> :crypto.hash_update(hash, block) end)
        |> :crypto.hash_final()
    end
end