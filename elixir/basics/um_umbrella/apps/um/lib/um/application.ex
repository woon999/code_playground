defmodule Um.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      Um.Repo,
      {DNSCluster, query: Application.get_env(:um, :dns_cluster_query) || :ignore},
      {Phoenix.PubSub, name: Um.PubSub},
      # Start the Finch HTTP client for sending emails
      {Finch, name: Um.Finch}
      # Start a worker by calling: Um.Worker.start_link(arg)
      # {Um.Worker, arg}
    ]

    Supervisor.start_link(children, strategy: :one_for_one, name: Um.Supervisor)
  end
end
