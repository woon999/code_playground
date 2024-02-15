defmodule Um.Repo do
  use Ecto.Repo,
    otp_app: :um,
    adapter: Ecto.Adapters.Postgres
end
