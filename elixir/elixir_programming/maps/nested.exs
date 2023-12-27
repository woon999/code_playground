defmodule Customer do
    defstruct name: "", company: ""
end

defmodule BugReport do
    defstruct owner: %Customer{}, details: "", severity: 1
end 


# iex
# report = %BugReport{owner: %Customer{name: "Dave", company: "Pragmatic"}, details: "broken"}
# => %BugReport{
#   owner: %Customer{name: "Dave", company: "Pragmatic"},
#   details: "broken",
#   severity: 1
# }
# report.owner.company
# => "Pragmatic"
# put_in(report.owner.company, "Acme")
# => %BugReport{
#   owner: %Customer{name: "Dave", company: "Acme"},
#   details: "broken",
#   severity: 1
# }
# update_in(report.owner.company, &String.upcase/1)
# => %BugReport{
#   owner: %Customer{name: "Dave", company: "PRAGMATIC"},
#   details: "broken",
#   severity: 1
# }


nested = %{
    buttercup: %{
        actor: %{
            first: "Robin",
            last: "Wright"
        },
        role: "princess"
    },
    westly: %{
        actor: %{
            first: "Cary",
            last: "Elwes@!"
        },
        role: "farm boy"
    }
}

###### 매크로 호출
IO.puts "marco call------------------"
IO.inspect put_in(nested.westly.actor.last, "Elwes")
# => %{
#   buttercup: %{
#     actor: %{first: "Robin", last: "Wright"},
#     role: "princess"
#   },
#   westly: %{
#     actor: %{first: "Cary", last: "Elwes"},
#     role: "farm boy"
#  }
# }

IO.inspect update_in(nested.westly.actor.last, &String.upcase/1)
# => %{
#   buttercup: %{
#     actor: %{first: "Robin", last: "Wright"},
#     role: "princess"
#   },
#   westly: %{
#     actor: %{first: "Cary", last: "ELWES"},
#     role: "farm boy"
#  }
# }

###### 함수 호출
IO.puts "function call------------------"
IO.inspect get_in(nested, [:buttercup])
# => %{actor: %{first: "Robin", last: "Wright"}, role: "princess"}

IO.inspect get_in(nested, [:buttercup, :actor])
# => %{first: "Robin", last: "Wright"}

IO.inspect get_in(nested, [:buttercup, :actor, :first])
# => "Robin"

IO.inspect put_in(nested, [:westly, :actor, :last], "Elwes")
# => %{
#   buttercup: %{
#     actor: %{first: "Robin", last: "Wright"},
#     role: "princess"
#   },
#   westly: %{
#     actor: %{first: "Cary", last: "Elwes"},
#     role: "farm boy"
#  }
# }
