import json

arr = []
with open('car_categories_models.json', 'r', encoding='utf8') as json_data:
    data = json.load(json_data)
    #print(data["results"])
    for x in data: 
    #    del x['objectId'] 
    #    del x['updatedAt'] 
    #    del x['createdAt'] 
    #   arr.append(x)
        arr.append(x)
print(arr)