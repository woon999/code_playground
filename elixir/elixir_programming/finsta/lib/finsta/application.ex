defmodule Finsta.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      FinstaWeb.Telemetry,
      Finsta.Repo,
      {DNSCluster, query: Application.get_env(:finsta, :dns_cluster_query) || :ignore},
      {Phoenix.PubSub, name: Finsta.PubSub},
      # Start the Finch HTTP client for sending emails
      {Finch, name: Finsta.Finch},
      # Start a worker by calling: Finsta.Worker.start_link(arg)
      # {Finsta.Worker, arg},
      # Start to serve requests, typically the last entry
      FinstaWeb.Endpoint
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: Finsta.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  @impl true
  def config_change(changed, _new, removed) do
    FinstaWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
