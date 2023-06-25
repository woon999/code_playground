# https://www.codewars.com/kata/554e4a2f232cdd87d9000038/train/ruby

def DNA_strand(dna)
  dna.tr('ATCG', 'TAGC')
end

p DNA_strand("AAAA") =="TTTT"
p DNA_strand("ATTGC") == "TAACG"
p DNA_strand("GTAT") =="CATA"