def nb_year(p0, percent, aug, p)
  year = 0
  while p0 < p
    p0 = (p0 + p0*percent*0.01 + aug).to_i
    year += 1
  end
  year
end

p nb_year(1500, 5, 100, 5000) == 15
p nb_year(1500000, 2.5, 10000, 2000000) == 10
p nb_year(1500000, 0.25, 1000, 2000000) == 94