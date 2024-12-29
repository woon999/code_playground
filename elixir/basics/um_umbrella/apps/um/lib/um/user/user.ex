defmodule Um.User do
  use Ecto.Schema
  import Ecto.Changeset

  schema "users" do
    field :name, :string
    timestamps()
  end

  def changeset(user, params \\ %{}) do
    user
    |> cast(params, [:name])
  end
end
