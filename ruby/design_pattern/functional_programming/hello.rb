FindByLogin = proc do |login|
  puts "login: #{login}"
  login
end

ConfirmUserAccount = proc do |user|
  puts "confirm user account: #{user}"
  user
end

SendConfirmationNotification = proc do |user|
  puts "send confirmation notification: #{user}"
  user
end

(FindByLogin >>
  ConfirmUserAccount >>
  SendConfirmationNotification).call("gregnavis")
# login: gregnavis
# confirm user account: gregnavis
# send confirmation notification: gregnavis


