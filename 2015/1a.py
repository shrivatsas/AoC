# To what floor do the instructions take Santa?

import pathlib
content = pathlib.Path('1.input').read_text()

counter = sum(1 if c == '(' else -1 for c in content)
print(counter)