defmodule Issues.MixProject do
  use Mix.Project

  def project do
    [
      app: :issues,
      escript: escript_config(),
      version: "0.1.0",
      elixir: "~> 1.15",
      start_permanent: Mix.env() == :prod,
      name: "Issues",
      source_url: "",
      deps: deps()
    ]
  end

  def application do
    [
      extra_applications: [:logger]
    ]
  end

  defp deps do
    [
      { :httpoison, "~> 1.8" },
      { :poison, "~> 5.0" },
      { :ex_doc, "~> 0.25" },
      { :earmark, "~> 1.4" }
    ]
  end

  defp escript_config do
    [
      main_module: Issues.CLI
    ]
  end
end
