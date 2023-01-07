import pathlib

s = pathlib.Path('../8.input')

with s.open() as f:
  lines = [ln.strip() for ln in f]

def encode(line):
  s = ''.join('\\' + c if c in ['"', '\\'] else c for c in line)
  return len(s) + 2

str_code = [len(ln) for ln in lines]
en_code = [encode(ln) for ln in lines]
print(sum(en_code) - sum(str_code))