import json, requests
from requests.api import request   

make_arr = []
with open('car_model_list.json', 'r', encoding='utf8') as json_data:
    data = json.load(json_data)
    for obj in data["results"]:
        make = obj["make"]
        model = obj["model"]
        try:
            make_arr.append(make)
            #r = requests.post('http://localhost:25001/models/new/' + str(make) + "/" + str(model))
        except Exception as ex:
            print(ex)




new_make_arr = list(dict.fromkeys(make_arr))
for x in new_make_arr:
   r = requests.post('http://localhost:25001/makes')
   print("Added Make:", r.text)
print(len(new_make_arr))


with open('car_model_list.json', 'r', encoding='utf8') as json_data:
    data = json.load(json_data)
    for obj in data["results"]:
        make = obj["make"]
        model = obj["model"]
        try:
            #make_arr.append(make)
            r = requests.post('http://localhost:25001/models)
            print("Added Model:", r.text)
        except Exception as ex:
            print(ex)

