'''
The Airport class is designed to store the various information
specific to the airports.

@author: Joshua Harris
'''

class Airport:
   
    def __init__(self, code, name, country, continent, timezone, coordinates, population, region):
        '''
        Constructor for the Airport class 
        '''
        self.code = code
        self.name = name
        self.country = country
        self.continent = continent
        self.timezone = timezone
        self.coordinates = coordinates
        self.population = population
        self.region = region
        self.flights = {}   
        

        
        