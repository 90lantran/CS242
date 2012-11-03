import math
import Queries

'''
This is responsible for providing the information on a route for the user.

@author: Joshua
'''

def routeInformation(airportList, translator):
    query = Queries.Queries(airportList, translator)
    
    print("City List:")
    printCityList(query.getCityList())
    isValidCity = False
    while(not isValidCity):
        print("Select a city for querying on or q to go back:")
        city = raw_input()
        isValidCity = city in translator
        if(input == "q"):
            return
    
    
    code = translator[city]
    startAirport = airportList[code]
    airport = startAirport
    stops = [code]
    legs = []
    
    while(True):
        output = ""
        for destination in airport.flights:
            output += destination + ", "
        
        code = ""
        isValidCode = False
        
        while(not isValidCode):
            print("Please choose a destination or hit return when done to calculate (Must enter at least 1 choice).")
            print(output)
            code = raw_input()
            isValidCode = (code in airport.flights) or (code == "")
        
        if(code == ""):
            break    
        legs.append(airport.flights[code])
        stops.append(code)
        airport = airportList[code]
    
    print("Total Distance = " + str(calcTotalDistance(legs)) + " km")
    print("Total Cost = $" + str(calcTotalCost(legs)))
    print("Total Time = " + str(calcTotalTime(legs, stops, airportList)) + " hrs")
    
def calcTotalDistance(legs):
    sum = 0
    for leg in legs:
        sum += leg
    return sum

def calcTotalCost(legs):
    cost = legs[0] * .35
    
    for i in range(1, len(legs)):
        cost -= legs[i] * .05
    
    if(cost <= 0):
        cost = 0
    
    return cost

def calcTotalTime(legs, stops, airportList):
    totalTime = 0
    acceleration = 1406.25 #km/hr^2
    
    for i in range(len(legs)):
        if(legs[i] < 400):
            distance = legs[i]/2
            time = math.sqrt((2 * distance) / acceleration)
            totalTime += time * 2
        else:
            totalTime += math.sqrt((2 * 400) / acceleration)
            distance = legs[i] - 400
            totalTime += distance / 750
        
        if(i + 2 < len(stops)):
            numberOfOutgoingFlights = len(airportList[stops[i+1]].flights)
            totalTime += 2 - numberOfOutgoingFlights/6
    
    return totalTime
            
            
            
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