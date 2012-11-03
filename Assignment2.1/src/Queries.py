import Airport

'''
This servers as an API for the queries of CSAir

@author: Joshua Harris
'''

class Queries:
    
    '''
    Constructor for Queries
    '''
    def __init__(self, airportList, cityToCode):
        self.airports = airportList
        self.translator = cityToCode
    
    '''
    returns the Code for the given city
    '''
    def getCode(self, city):
        return self.translator[city]
    
    '''
    returns the Country for the given city
    '''
    def getCountry(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].country
    
    '''
    returns the Continent for the given city
    '''
    def getContinent(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].continent
    
    '''
    returns the Time Zone for the given city
    '''
    def getTimeZone(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].timezone
    
    '''
    returns the Longitude and Latitude for the given city
    '''
    def getCoordinates(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].coordinates
    
    '''
    returns the Population for the given city
    '''
    def getPopulation(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].population
    
    '''
    returns the Region for the given city
    '''
    def getRegion(self, city):
        # get the correct code first to look up the airport
        code = self.translator[city]
        return self.airports[code].region
    
    '''
    returns a list of Cities and the Distances to them from the given city
    that can be traveled to from the given city in a non stop flight
    '''
    def getCityFlights(self, city):
        code = self.translator[city]
        flights = self.airports[code].flights
        return flights
    
    '''
    getCities returns a list of cities on the given airports
    '''
    def getCityList(self):
        cityList = []
        for airportCode in self.airports:
            airport = self.airports[airportCode]
            cityList.append(airport.name)
            
        return cityList
    