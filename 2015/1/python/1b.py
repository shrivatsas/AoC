# What is the position of the character that causes Santa to first enter the basement?

import pathlib
content = pathlib.Path('../1.input').read_text()

counter = 0
for idx, c in enumerate(content):
  counter += 1 if c == '(' else -1
  if counter == -1:
    print(idx+1)
    break