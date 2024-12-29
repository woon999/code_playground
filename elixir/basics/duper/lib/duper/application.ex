defmodule Duper.Application do
  use Application

  @impl true
  def start(_type, _args) do
    children = [
      Duper.Results,
      { Duper.PathFinder, "."},
      Duper.WorkerSupervisor,
      { Duper.Gatherer, 1}
    ]

    opts = [strategy: :one_for_all, name: Duper.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
