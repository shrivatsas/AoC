import pathlib

p = pathlib.Path('2.input')

with p.open() as f:
  lines = f.readlines()

boxes = [tuple(map(int, line.strip().split('x'))) for line in lines]

wrapper = sum((2 * l * w) + (2 * w * h) + (2 * h * l) + min(l*w, w*h, l*h) for (l,w,h) in boxes)
print(wrapper)
