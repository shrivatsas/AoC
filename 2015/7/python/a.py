import pathlib
import time

p = pathlib.Path('../7.input')

with p.open() as f:
  instructions = f.readlines()

wires = {}

def ios(s):
  try:
    return int(s)
  except ValueError:
    return s

def parse(inst):
  lhs = inst.split()
  if len(lhs) == 1:
    return 'Assign', ios(lhs[0]), None
  elif len(lhs) == 2:
    return lhs[0], ios(lhs[1]), None
  elif len(lhs) == 3:
    if lhs[1] in ['RSHIFT', 'LSHIFT']:
      return lhs[1], lhs[0], ios(lhs[2])
    return lhs[1], ios(lhs[0]), ios(lhs[2])

def solve(wire):
  print(f"solving for {wire} : {wires[wire] if wire in wires else wire}")
  if isinstance(wire, int):
    print(f"> returning {wire}")
    return wire
  if isinstance(wire, str) and wire.isdigit():
    print(f">> returning {int(wire)}")
    return int(wire)
  elif isinstance(wires[wire], int):
    print(f">>> returning {wires[wire]}")
    return wires[wire]

  op, a, b = parse(wires[wire])
  if op == 'Assign':
    wires[wire] = solve(a)
    return wires[wire]
  elif op == 'NOT':
    wires[wire] = ~solve(a)
    return wires[wire]
  elif op == 'AND':
    wires[wire] = solve(a) & solve(b)
    return wires[wire]
  elif op == 'OR':
    wires[wire] = solve(a) | solve(b)
    return wires[wire]
  elif op == 'LSHIFT':
    wires[wire] = solve(a) << b
    return wires[wire]
  elif op == 'RSHIFT':
    wires[wire] = solve(a) >> b
    return wires[wire]

for inst in instructions:
  lhs, rhs = inst.strip().split(' -> ')
  wires[rhs] = int(lhs) if lhs.isdigit() else lhs

print(solve('a'))