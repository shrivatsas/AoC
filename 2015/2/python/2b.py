import pathlib

p = pathlib.Path('2.input')

with p.open() as f:
  lines = f.readlines()

boxes = [list(map(int, line.strip().split('x'))) for line in lines]

ribbon = 0
for sides in boxes:
  tail_sides = sorted(sides, reverse=True)[1:]
  ribbon += sum(tail_sides) * 2 + sides[0] * sides[1] * sides[2]

print(ribbon)
