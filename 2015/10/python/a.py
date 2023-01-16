import re

def look_and_say(n):
    return ''.join([str(len(match.group(0))) + match.group(0)[0] for match in re.finditer(r"(\d)\1*", n)])

result = "1113222113"
for _ in range(50):
    result = look_and_say(result)

print(len(result))