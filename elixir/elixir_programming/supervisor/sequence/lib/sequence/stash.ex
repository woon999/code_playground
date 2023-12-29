defmodule Sequence.Stash do
    use GenServer

    @me __MODULE__

    def start_link(initial_number) do
        GenServer.start_link(__MODULE__, initial_number, name: @me)
    end

    def get() do
        GenServer.call(@me, { :get })
    end

    def update(number) do
        GenServer.cast(@me, { :update, number })
    end

    # server impl
    def init(initial_number) do
        {:ok, initial_number}
    end

    def handle_call({ :get }, _from, current_number) do
        {:reply, current_number, current_number}
    end

    def handle_cast({ :update, number }, _current_number) do
        { :noreply, number }
    end
end