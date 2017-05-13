import json

fp = open("7394-output.txt", "r")

j = json.load(fp)
#print json.dumps(j, indent=4, separators=(',',': '))

#print j["hannah"][1]

print j["hannah"][1]["Metadata"][0]
print "--"

#[[0,[1,2,3,4,5,6,7,8,9]]]
data = []
templist = []

correctUser = "niki"
for user in j:
	if user == correctUser:
		for attempt in j[user]:
			templist = []
			for key in attempt["Keys"]:
				templist.append(key["Dwell"])
				templist.append(key["Fight"])
			templist.append(attempt["Metadata"][0]["Total Time"])
			print "-"
			data.append([[1,templist]])
	else:
		for attempt in j[user]:
			templist = []
			for key in attempt["Keys"]:
				templist.append(key["Fight"])
				templist.append(key["Dwell"])
			templist.append(attempt["Metadata"][0]["Total Time"])
			print "-"
			data.append([0,templist])

print data[0]

def normData(data):
	for i in range(9):
		for i in range(len(data)):
			data[i][]
