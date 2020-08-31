#Importing the necessary libraries
import json
import re
# Method that reads and returns the contents of the json file
def readJson():
    with open("contacts.json") as entity:
        data = json.load(entity)
    return data
# Method that displays the validation result and the full name 
def checkContacts():
    #Iterating through the document
    for item in readJson():
        #prints out the validation results from validating email address and phone number
        print(item["fullName"], validateData(item["emailAddress"], item["phoneNumber"]))
#Method to check the number of validation errors grouped by city
def checkCity():
    city = {} #Creating empty dictionary to store the city and its validation error count
    #Iterating through the document
    for item in readJson():
        city[item['cityName']] = 0 #Initializing the error count to zero for every city in the document
    #Iterating through the document
    for entity in readJson():
        #Checking if the city is in the dictionary and updating the error count as necessary
        if entity['cityName'] in city.keys():
            if(validateData(entity['emailAddress'], entity['phoneNumber']) != 'Valid'):
                if(validateData(entity['emailAddress'], entity['phoneNumber']) =="Email is invalid" or
                   validateData(entity['emailAddress'], entity['phoneNumber']) =="Phone is invalid" ):
                    city[entity['cityName']] += 1 #Incrementing the error count by one if either email or phone is invalid
                else:
                    city[entity['cityName']] += 2 #Incrementing the error count by two if both email and phone are invalid
    city_descending = sorted(city.items(), key=lambda x: x[1], reverse=True) #Sorting the dictionary in descending order
    return city_descending #Return the sorted dictionary
# Method to validate email and phone
def validateData(email, phone):
    #Initializing the booleans to true for valid email and valid phone
    isValidEmail = True
    isValidPhone = True
    emailRegex = '^[a-z0-9]+[\._]?[a-z0-9]+[@]\w+[.]\w{2,3}$' #Regex for email
    phoneRegex = "\w{3}-\w{3}-\w{4}" #Regex for phone
    #Validating the email
    if not re.search(emailRegex,email): 
        isValidEmail = False
    #Validating the phone
    if not re.search(phoneRegex, phone):
        isValidPhone = False
    #Returning appropriate messges based on the validation
    if(isValidEmail and isValidPhone):
        return "Valid"
    if(not isValidEmail and isValidPhone):
        return "Email is invalid"
    if(isValidEmail and not isValidPhone):
        return "Phone is invalid"
    if(not isValidEmail and not isValidPhone):
        return "Email and Phone are invalid"
#Uncomment the elow code to check the program
#print(checkContacts())
#print(checkCity())

    
    
  
  
