
import json, requests
from requests.api import request   
import hashlib

import random

def createUsers(amount) :
    ob = 1
    while(ob < amount):
        hash = random.getrandbits(1000)
        email = str(hash) + "@mail.com"
        data = requests.post("http://localhost:25000/users", json={"firstName": "fname", "lastName" : "lname", "email" : email, "password" : "1234"})
        ob = ob+1

def createListingsForUsers(amount):
    ob = 0
    carList = requests.get("http://localhost:25000/cars?size=9999999").json()
    userList = requests.get("http://localhost:25000/users").json()

    carList = carList["content"]
    while(ob < amount):
        car = random.choice(carList)
        user = userList[random.randrange(0, 24)]
        title = "Selling my " + str(car["make"]["name"])
        userId = user["id"]

        carMake = car["make"]
        carModel = car["model"]
        listing = {
            "id": "123",
            "seller": userId,
            "title": "AAAAAAAAAAAAAAAAAA",
            "description": "Test.",
            "price": 1234,
            "km": 1234,
            "car": car,
            "status": "ACTIVE",
            "created_on": "2021-05-29T12:36:32.699+0000"
        }
            
        print(listing)

        requests.post("http://localhost:25001/listings", json = (listing))
        ob = ob+1
        
    

##createUsers(100)
createListingsForUsers(5000)