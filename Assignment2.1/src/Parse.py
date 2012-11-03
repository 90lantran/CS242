import json
import Airport

'''
This is the object responsible for parsing the JSON file and 
creating the objects

@author: Joshua Harris
'''

class Parser(object):

    def __init__(self):
        self.airportList = {}
        self.cityToCode = {}
    
    '''
    This fuction when called will parse a list of airports from
    the JSON text file
    '''            
    def parse(self, fileName):
        JSONParsed = json.load(open(fileName))
        
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
            
            airport = Airport.Airport(code, name, country, continent, \
                                      timezone, coords, population, region)
            self.airportList[airport.code] = airport
            
            self.cityToCode[name] = code
        
        # parse through the routes    
        for route in JSONParsed['routes']:    
            departureCode = route['ports'][0]
            arrivalCode = route['ports'][1]
            distance = route['distance']
            
            # find the correct airport and add the route to the flights hash table  
            self.airportList[departureCode].flights[arrivalCode] = distance
        
    '''
    This function will write the network's information to a file called 
    saveFile.txt in the JSON format
    '''
    def saveToFile(self):
        root = {}
        metros = []
        routes = []
        
        # Make the objects for the JSON output
        for airport in self.airportList.itervalues():
            airportDic = {}
            airportDic["code"] = airport.code
            airportDic["name"] = airport.name
            airportDic["country"] = airport.country
            airportDic["continent"] = airport.continent
            airportDic["timezone"] = airport.timezone
            airportDic["coordinates"] = airport.coordinates
            airportDic["population"] = airport.population
            airportDic["region"] = airport.region
            
            metros.append(airportDic)
            
            for destination in airport.flights:
                distance = airport.flights[destination]
                routeDic = {}
                routeDic["ports"] = [airport.code, destination]
                routeDic["distance"] = distance
                
                routes.append(routeDic)
            
        root["metros"] = metros
        root["routes"] = routes

        # Write the JSON output to the file
        saveFile = open("saveFile.txt", "w")
        saveFile.write(json.dumps(root))
        
