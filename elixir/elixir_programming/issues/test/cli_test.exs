defmodule CliTest do
    use ExUnit.Case
    doctest Issues 

    import Issues.CLI, only: [parse_args: 1, sort_into_descending_order: 1]

    test "-h나 --help가 옵션으로 파싱되면 :help가 반환된다" do
        assert parse_args(["-h", "anything"]) == :help
        assert parse_args(["--help", "anything"]) == :help
    end

    test "값을 3개 전달하면 값 3개가 반환된다" do
        assert parse_args(["user", "project", "99"]) == {"user", "project", 99}
    end

    test "값을 2개 전달하면 count는 default 값으로 반환된다" do
        assert parse_args(["user", "project"]) == {"user", "project", 4}
    end

    test "created_at 기준으로 내림차순 정렬된다" do
        result = sort_into_descending_order(fake_create_at_list(["b", "a", "c"]))
        issues = for issue <- result, do: Map.get(issue, "created_at")
        assert issues == ~w{c b a}
    end

    defp fake_create_at_list(list) do
        for item <- list, do: %{"created_at" => item, "other" => "xxx"}
    end
end