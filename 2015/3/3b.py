import pathlib
from typing import Tuple

walk = pathlib.Path('3.input').read_text()

def stringify(pos: Tuple):
  return str(pos[0]) + str(pos[1])

posA = (0, 0)
posB = (0, 0)

visited = set()
visited.add(stringify(posA))

def move(pos: Tuple, c: str) -> Tuple:
  if c == '^':
    return (pos[0], pos[1] + 1)
  elif c == 'v':
    return (pos[0], pos[1] - 1)
  elif c == '>':
    return (pos[0] + 1, pos[1])
  elif c == '<':
    return (pos[0] - 1, pos[1])
  else:
    raise ValueError(f'Unexpected character: {c}')

for idx, c in enumerate(walk):
  if idx % 2 == 0:
    posA = move(posA, c)
    visited.add(stringify(posA))
  else:
    posB = move(posB, c)
    visited.add(stringify(posB))

print(len(visited))