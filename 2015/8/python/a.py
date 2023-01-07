import pathlib

s = pathlib.Path('../8.input')

with s.open() as f:
  lines = [ln.strip() for ln in f]

def parse(line):
  esc = False
  hx = ''
  s = ''
  for c in line:
    if esc == False and c == '\\':
      esc = True
    elif c in ['"', '\\']:
      if esc:
        esc = False
        s += c
    elif c == 'x' and esc:
      continue
    elif esc:
      if len(hx) < 2:
        hx += c
      if len(hx) == 2:
        s += chr(int(hx, 16))
        hx = ''
        esc = False
    else:
      s += c

  return len(s)

str_code = [len(ln) for ln in lines]
mem_code = [parse(ln) for ln in lines]
print(sum(str_code) - sum(mem_code))