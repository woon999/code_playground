=begin
   Ruby는 몇 가지 파이프라이닝 프리미티브를 제공한다.
   Proc와 Method는 파이프라인에서 사용할 수 있는 #<< 및 #>>에 응답한다.
=end

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


