# Github ssues
1. mix build
```
mix escript.build
```

2. get git issues
- "#{@github_url}/repos/#{user}/#{project}/issues"
```
$ ./issues {user} {project} {count}
```

ex)
```
> ./issues elixir-lang elixir 4
numbe | created_at           | title                                                                                                                 
------+----------------------+-----------------------------------------------------------------------------------------------------------------------
13165 | 2023-12-11T00:52:21Z | Elixir silently removes exclamation marks and reports no such file (Windows 10)                                       
13166 | 2023-12-11T06:41:56Z | Check for exclamation marks on Windows                                                                                
13169 | 2023-12-11T19:48:23Z | Need ability to add custom CA cert to `mix deps.get` and similar when running behind corporate firewall (Elixir 1.15+)
13190 | 2023-12-15T07:37:52Z | Logger crashing when metadata contains time key                    
```
