defmodule Duper.Gatherer do
    use GenServer

    @me Gatherer

    # api 

    def start_link(worker_count) do
        GenServer.start_link(__MODULE__, worker_count, name: @me)
    end

    def done() do
        GenServer.cast(@me, :done)
    end

    def result(path, hash) do
        GenServer.cast(@me, {:result, path, hash})
    end

    # server 
    def init(worker_count) do
        Process.send_after(self(), :kickoff, 0) # 0초 기다린 후 메시지 큐에 넣음. init 실행 종료 후에 handle_info(:kickoff, worker_count) 실행.
        {:ok, worker_count}
    end

    def handle_info(:kickoff, worker_count) do
        1..worker_count
        |> Enum.each(fn _ -> Duper.WorkerSupervisor.add_worker() end)
        {:noreply, worker_count}
    end

    def handle_cast(:done, _worker_count = 1) do
        report_results()
        System.halt(0)
    end

    def handle_cast(:done, worker_count) do
        {:noreply, worker_count - 1}
    end

    def handle_cast({:result, path, hash}, worker_count) do
        Duper.Results.add_hash_for(path, hash)
        {:noreply, worker_count}
    end

    def report_results() do
        IO.puts "Results:\n"
        Duper.Results.find_duplicates()
        |> Enum.each(&IO.inspect/1)
    end
end