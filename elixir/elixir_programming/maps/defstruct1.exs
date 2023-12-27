defmodule Attendee do
    defstruct name: "", paid: false, over_18: true

    def may_attend_party?(attendee = %Attendee{}) do
        attendee.paid && attendee.over_18
    end

    def print_vip_badge(%Attendee{name: name}) when name != "" do
        IO.puts "VIP - #{name}"
    end

    def print_vip_badge(%Attendee{}) do
        IO.puts "Unknown"
    end
end

# iex script
# s1 = %Attendee{}
# => %Attendee{name: "", paid: false, over_18: true}
# s2 = %Attendee{name: "Lee", paid: true, over_18: true}
# => %Attendee{name: "Lee", paid: true, over_18: true}
# Attendee.may_attend_party?(s1)
# => false
# Attendee.may_attend_party?(s2)
# => true
# Attendee.print_vip_badge(s1)
# => Unknown
# Attendee.print_vip_badge(s2)
# => VIP - Lee
