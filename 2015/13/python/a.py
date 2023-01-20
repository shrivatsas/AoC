import pathlib
import re
from itertools import permutations

r = re.compile(r'([a-z]+) would (gain|lose) ([0-9]+) happiness units by sitting next to ([a-z]+)', re.IGNORECASE)
p = pathlib.Path('../13.input')
with p.open() as f:
  data = [r.findall(d)[0] for d in f.readlines()]
  attendees = {d[0] for d in data}
  attendees.add('me')

  relations = { a: { b: 0 for b in attendees} for a in attendees}
  for d in data:
    relations[d[0]][d[3]] = int(d[2]) if d[1] == 'gain' else -int(d[2])

max_happiness = 0
for i in permutations(attendees):
  happiness = 0
  for j in range(len(i)):
    happiness += relations[i[j]][i[j-1]]
    happiness += relations[i[j]][i[j+1-len(i)]]
  max_happiness = max(max_happiness, happiness)
  print(max_happiness)
