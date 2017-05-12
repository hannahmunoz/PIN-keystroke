import json

fp = open("7394-output.txt", "r")

j = json.load(fp)
print "Whole json"
print json.dumps(j, indent=4, separators=(',',': '))

print "--"
print type(j)
print j["hannah"]