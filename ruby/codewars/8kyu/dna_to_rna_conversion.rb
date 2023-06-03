# https://www.codewars.com/kata/5556282156230d0e5e000089/train/ruby

def dna_to_rna(dna)
  dna.gsub('T', 'U')
end

p dna_to_rna("TTTT") == "UUUU"
p dna_to_rna("GCAT") == "GCAU"
p dna_to_rna("GACCGCCGCC") == "GACCGCCGCC"