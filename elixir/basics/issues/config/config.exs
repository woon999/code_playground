import Config
config :issues, github_url: "https://api.github.com"
config :logger, compile_time_purge_matching: [
    [level_lower_than: :info]
]

# if use config by environment 
# ex) dev.exs, test.exs, prod.exs
# import_config "#{config_env()}.exs" 