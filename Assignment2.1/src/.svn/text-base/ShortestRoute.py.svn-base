import Queries
'''
Uses Djistra's Algorithm to find the shortest route for the 
user between two cities.
@author: Joshua
'''

def findShortestRoute(airportList, translator):
    
    query = Queries.Queries(airportList, translator)
    start = ""
    finish = ""
    
    print("City List:")
    printCityList(query.getCityList())
    isValidCity = False
    while(not isValidCity):
        print("Select a city for the origin or q to go back:")
        start = raw_input()
        isValidCity = start in translator
        if(input == "q"):
            return
    
    isValidCity = False
    while(not isValidCity):
        print("Select a city for the finish or q to go back:")
        finish = raw_input()
        isValidCity = finish in translator
        if(input == "q"):
            return
    
    unvisited = []
    for airport in airportList.itervalues():
        unvisited.append(airport.code)
        
    

'''
Quick function to format the cities printed out.
'''
def printCityList(cities):
    citiesPerLine = 12
    count = 0
    line = ""
    
    for city in cities:
        line += city + ", "
        count += 1
        if(count == citiesPerLine):
            print(line)
            count = 0
            line = ""
    if(line != ""):
        print(line[:-2])
        
'''
This function is to generally print out lists nicely
'''
def printList(items):
    output = ""
    for item in items:
        output += item + ", "
    print output