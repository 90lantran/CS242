import Airport

'''
This servers as an API for the statistical queries of CSAir

@author: Joshua Harris
'''
from symbol import if_stmt
from compiler.ast import IfExp

class Statistics:
    
    '''
    Constructor for Queries
    '''
    def __init__(self, airportList, cityToCode):
        self.airports = airportList
        self.translator = cityToCode
    
    '''
    Helper function for translation from an airport code to a city
    '''
    def translate(self, airportCode):
        return self.translator[airportCode]
    
    
    '''
    getLongestFlight will return a list containing the two cities
    that the flight occurs from and the distance
    '''    
    def getLongestFlight(self):
        max = 0
        maxFlight = []
        
        # search through each airport and outgoing routes for the longest
        for airport in self.airports.itervalues():
            for flight in airport.flights.keys():
                if(airport.flights[flight] > max):
                    max = airport.flights[flight]
                    maxFlight = [airport.name, \
                                 self.airports[flight].name, max]
        
        return maxFlight
    
    def getShortestFlight(self):
        min = 0
        minFlight = []
        
        # search through each airport and outgoing routes for the longest
        for airport in self.airports.itervalues():
            for flight in airport.flights.keys():
                if(min == 0):
                    min = airport.flights[flight]
                    minFlight = [airport.name, \
                                 self.airports[flight].name, min]
                elif(airport.flights[flight] < min):
                    min = airport.flights[flight]
                    minFlight = [airport.name, \
                                 self.airports[flight].name, min]
        
        return minFlight
        
        
    def getAverageFlightDistance(self):
        sum = 0
        count = 0
        # search through each airport and outgoing routes for the longest
        for airport in self.airports.itervalues():
            for flight in airport.flights.keys():
                sum += airport.flights[flight]
                count += 1
        
        return sum / count     
        
        
    def getLargestCity(self):
        max = 0
        largestCity = []
        
        # search through each airport and outgoing routes for the longest
        for airport in self.airports.itervalues():
            if(airport.population > max):
                    max = airport.population
                    largestCity = [airport.name, max]
        
        return largestCity    
        
    def getSmallestCity(self):
        min = 100000000
        smallestCity = []
        
        # search through each airport and outgoing routes for the longest
        for airport in self.airports.itervalues():
            if(airport.population < min):
                    min = airport.population
                    smallestCity = [airport.name, min]
        
        return smallestCity
    
    def getAverageCitySize(self):
        sum = 0
        count = 0
        
        for airport in self.airports.itervalues():
            sum += airport.population
            count += 1
        
        return sum / count
    
    def getContinentsList(self):
        continentList = {}
        
        for airport in self.airports.itervalues():
            if(airport.continent in continentList):
                continentList[airport.continent].append(airport.name)
            else:
                continentList[airport.continent] = [airport.name]
        
        return continentList
    
    def getHubCity(self):
        cityList = []
        max = 0
        for airport in self.airports.itervalues():
            if(len(airport.flights) > max):
                cityList = [airport.name]
                max = len(airport.flights)
            elif(len(airport.flights) == max):
                cityList.append(airport.name) 
        return cityList
                
                
                
                
                
                
                
                
                
                
                
                
                