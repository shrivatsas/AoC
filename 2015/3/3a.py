import pathlib
from typing import Tuple

walk = pathlib.Path('3.input').read_text()

def stringify(pos: Tuple):
  return str(pos[0]) + str(pos[1])

pos = (0, 0)

visited = set(stringify(pos))

for c in walk:
  if c == '^':
    pos = (pos[0], pos[1] + 1)
  elif c == 'v':
    pos = (pos[0], pos[1] - 1)
  elif c == '>':
    pos = (pos[0] + 1, pos[1])
  elif c == '<':
    pos = (pos[0] - 1, pos[1])
  else:
    raise ValueError(f'Unexpected character: {c}')
  visited.add(stringify(pos))

print(len(visited))