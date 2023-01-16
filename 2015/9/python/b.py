import pathlib
import re
from itertools import permutations

p = pathlib.Path('../9.input')
with p.open() as f:
  data = [d.strip() for d in f.readlines()]

def key(k):
  return ";".join(k)

def add_to_map(m, k):
  if k not in m:
    m[k] = len(m)

def add_distance(m, d, res):
  if (res[0], res[1]) not in d:
    d[key(res[:2])] = int(res[2])
    d[key(reversed(res[:2]))] = int(res[2])

cities = set()
distances = {}
def map_cities_distances(data):
  for d in data:
    res = re.findall(r'([a-z]+) to ([a-z]+) = ([0-9]+)', d, re.IGNORECASE)[0]
    cities.add(res[0]), cities.add(res[1])
    add_distance(cities, distances, res)

map_cities_distances(data)

max_cost = 0
for p in list(permutations(cities)):
  cost = 0
  i = 0
  while i < len(p):
    j = len(p)
    while j > i+1:
      if key(p[i:j]) in distances:
        cost += distances[key(p[i:j])]
        distances[key(p[:j])] = cost
        i = j - 2
        break
      j -= 1
    i += 1    
  max_cost = max(max_cost, cost)

print(max_cost)