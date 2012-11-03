'''
This class is responsible for making all the modifcations to the Routes

@author: Joshua Harris
'''

'''
This function is responsible for handling the removal of a city from the
network.
'''
def removeCity(city, airportList, translator):
    cityCode = translator[city]
    del airportList[cityCode]
    
    for airport in airportList.itervalues():
        routes = airport.flights
        if(cityCode in routes):
            del routes[cityCode]
'''
Given the airport and the destination this will remove the route from the 
airport in the network.
'''
def removeRoute(airport, destCode):
    routes = airport.flights
    del routes[destCode]
    
'''
Given an Airport and the route information this will add the route into 
the network.
'''
def addRoute(airport, destCode, distance):
    routes = airport.flights
    routes[destCode] = distance
    
'''
This changes the Airport's Country
'''
def changeCountry(airport, country):
    airport.country = country

'''
This changes the Airport's Continent
'''
def changeContinent(airport, continent):
    airport.contitnent = continent
    
'''
This changes the Airport's Timezone
'''
def changeTimezone(airport, timezone):
    airport.timezone = timezone
    
'''
This changes the Airport's Region  
'''
def changeRegion(airport, region):
    airport.region = region
    
'''
This changes the Airport's population
'''
def changePopulation(airport, population):
    airport.population = population