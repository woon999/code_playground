defmodule FibSolver do
    def fib(scheduler) do
        send scheduler, { :ready, self() }
        receive do
            { :fib, n, client } ->
                send client, { :answer, n, fib_calc(n), self() }
                fib(scheduler)
            { :shutdown } ->
                exit(:normal)
        end
    end

    defp fib_calc(0), do: 0
    defp fib_calc(1), do: 1
    defp fib_calc(n), do: fib_calc(n-1) + fib_calc(n-2)
end

defmodule Scheduler do
    def run(num_of_processes, module, func, to_calc) do
        (1..num_of_processes)
        |> Enum.map(fn _ -> spawn(module, func, [self()]) end)
        |> schedule_processes(to_calc, [])
    end

    defp schedule_processes(processes, queue, results) do
        receive do
            {:ready, pid} when length(queue) > 0 ->
                [ next | tail ] = queue
                send pid, {:fib, next, self()}
                schedule_processes(processes, tail, results)
            {:ready, pid} ->
                send pid, {:shutdown}
                if length(processes) > 1 do
                    schedule_processes(List.delete(processes, pid), queue, results)
                else
                    Enum.sort(results, fn {n1, _}, {n2, _} -> n1 <= n2 end)
                end
            {:answer, n, answer, _pid} ->
                schedule_processes(processes, queue, [{n, answer} | results])
        end
    end
end

to_process = List.duplicate(37, 20)
IO.puts "Calculating #{length(to_process)} Fibonacci numbers..."

Enum.each 1..10, fn num_of_processes ->
    {time, results} = :timer.tc(Scheduler, :run, [num_of_processes, FibSolver, :fib, to_process])

    if num_of_processes == 1 do
        IO.puts inspect results
        IO.puts "\n #     시간 (s)"
    end
    :io.format "~2B     ~.2f~n", [num_of_processes, time/1_000_000.0]    
end