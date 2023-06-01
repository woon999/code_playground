def filter_list(l)
  l.grep(Numeric)
end

p filter_list([1,2,'a','b']) == [1,2]
p filter_list([1,'a','b',0,15]) ==  [1,0,15]
p filter_list([1,2,'aasf','1','123',123]) == [1,2,123]