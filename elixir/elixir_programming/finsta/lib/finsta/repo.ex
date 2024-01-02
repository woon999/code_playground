defmodule Finsta.Repo do
  use Ecto.Repo,
    otp_app: :finsta,
    adapter: Ecto.Adapters.Postgres
end
