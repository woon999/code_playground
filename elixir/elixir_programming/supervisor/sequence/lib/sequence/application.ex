defmodule Sequence.Application do
  use Application

  @impl true
  def start(_type, _args) do
    children = [
      {Sequence.Stash, 123},
      {Sequence.Server, nil}
    ]

    opts = [strategy: :rest_for_one, name: Sequence.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
