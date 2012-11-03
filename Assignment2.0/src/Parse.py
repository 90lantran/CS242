import json
import Airport

'''
This is the object responsible for parsing the JSON file and 
creating the objects

@author: Joshua Harris
'''

class Parser(object):

    def __init__(self):
        self.fileName = "data.txt"
        self.airportList = {}
        self.cityToCode = {}
    
    '''
    This fuction when called will parse a list of airports from
    the JSON text file
    '''            
    def parse(self):
        JSONParsed = json.load(open(self.fileName))
        
        # parse through the airport information
        for metro in JSONParsed['metros']:
            code = metro['code']
            name = metro['name']
            country = metro['country']
            continent = metro['continent']
            timezone = metro['timezone']
            coords = metro['coordinates']
            population = metro['population']
            region = metro['region']
            
            # Creat the airport object from the parsed data
            airport = Airport.Airport(code, name, country, continent, \
                                      timezone, coords, population, region)
            
            # Insert the new Airport into the Hash
            self.airportList[airport.code] = airport
            
            # Create a simple Hash that is in charge of later translating names to codes
            self.cityToCode[airport.name] = code
        
        # parse through the routes    
        for route in JSONParsed['routes']:    
            departureCode = route['ports'][0]
            arrivalCode = route['ports'][1]
            distance = route['distance']
            
            # find the correct airport and add the route to the flights hash table  
            self.airportList[departureCode].flights[arrivalCode] = distance
       
            
