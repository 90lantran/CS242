import Airport

'''
This servers as an API for the statistical queries of CSAir

@author: Joshua Harris
'''

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
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            for flight in airport.flights.keys():
                if(airport.flights[flight] > max):
                    max = airport.flights[flight]
                    maxFlight = [airport.name, self.airports[flight].name, max]
        
        return maxFlight
   
    '''
    getShortestFlight will return a list containing the two cities
    that the flight occurs from and the distance
    ''' 
    def getShortestFlight(self):
        min = 0
        minFlight = []
        
        # search through each airport and outgoing routes for the longest
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            for flight in airport.flights:
                
                if(min == 0):
                    min = airport.flights[flight]
                    minFlight = [airport.name, self.airports[flight].name, min]
                elif(airport.flights[flight] < min):
                    min = airport.flights[flight]
                    minFlight = [airport.name, self.airports[flight].name, min]
        
        return minFlight
        
    '''
    getAverageFlight will return the average distance of a flight
    in the CS Air Network
    '''    
    def getAverageFlightDistance(self):
        sum = 0
        count = 0
        # search through each airport and outgoing routes for the longest
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            for flight in airport.flights.keys():
                sum += airport.flights[flight]
                count += 1
        
        return sum / count     
        
    '''
    Returns the largest city by population in the CS Air Network
    '''    
    def getLargestCity(self):
        max = 0
        largestCity = []
        
        # search through each airport and outgoing routes for the longest
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            if(airport.population > max):
                    max = airport.population
                    largestCity = [airport.name, max]
        
        return largestCity    
        
    '''
    Returns the smallest city by population in the CS Air Network
    '''
    def getSmallestCity(self):
        min = 100000000
        smallestCity = []
        
        # search through each airport and outgoing routes for the longest
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            if(airport.population < min):
                    min = airport.population
                    smallestCity = [airport.name, min]
        
        return smallestCity
    
    '''
    Returns the Average city size by population in the CS Air Network
    '''
    def getAverageCitySize(self):
        sum = 0
        count = 0
        
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            sum += airport.population
            count += 1
        
        return sum / count
    
    '''
    Returns an dictionary of Continents and cities that are contained within them
    '''
    def getContinentsList(self):
        continentList = {}
        
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            # Check if the key already exits or not
            if( not (airport.continent in continentList)):
                continentList[airport.continent] = [airport.name]
            else:
                continentList[airport.continent].append(airport.name)
        
        return continentList
    
    '''
    Returns the city with the most amount of flights coming from it. This function
    will return multiple cities if there are ties.
    '''
    def getHubCity(self):
        cityList = []
        max = 0
        for airportKey in self.airports:
            airport = self.airports[airportKey]
            
            # Check if there is a new max or a tie, if there is a tie we append it
            if(len(airport.flights) > max):
                citylist = [airport.name]
            elif(len(airport.flights) == max):
                cityList.append(airport.name) 
                
        return cityList
                
                
                
                
                
                
                
                
                
                
                
                
                