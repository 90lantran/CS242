import Parse
import Airport
import Queries
import Statistics
import ModifyNetwork as modNet
import RouteCalc

'''
Created on Oct 3, 2012

@author: Joshua
'''

def main():
    print("Welcome to my CS Air Module")
    print("By: Joshua Harris")
    print("------------------------\n\n")
    fileName = raw_input("File to Parse: ")
    print("Parsing the JSON Text File")
    
    parser = Parse.Parser()
    parser.parse(fileName)
    
    print("Parse Complete!")
    print("Starting Normal Routine...")
    
    while(True):
        printMenu()
        input = raw_input()
        if(input == "1"):
            query(parser.airportList, parser.cityToCode)
        elif(input == "2"):
            stats(parser.airportList, parser.cityToCode)
        elif(input == "3"):
            print(drawMap(parser.airportList, parser.cityToCode))
        elif(input == "4"):
            editNetwork(parser.airportList, parser.cityToCode)
        elif(input == "5"):
            RouteCalc.routeInformation(parser.airportList, parser.cityToCode)
        elif(input == "6"):
            fileName = raw_input("File to Parse: ")
            parser.parse(fileName)
            print("Parsed!")
        elif(input == "7"):
            parser.saveToFile()
            print("Saved!")
        elif(input == "q"):
            break
    
def editNetwork(airportList, translator):
    query = Queries.Queries(airportList, translator)
    while(True):
        print("City List:")
        printCityList(query.getCityList())
        isValidCity = False
        while(not isValidCity):
            print("Select a city for querying on or q to go back:")
            city = raw_input()
            isValidCity = city in translator
            if(city == "q"):
                return 
            
        airport = airportList[translator[city]]
        
        
        while(True):
            printModNetworkMenu()
            userIn = raw_input()
            if(userIn == "1"):
                modNet.removeCity(city, airportList, translator)
                print(city + " removed!")
                # special case to break since no more actions can occur on this list
                break
            elif(userIn =="2"):
                output = ""
                for dest in airport.flights:
                    output += dest + ", "
                print(output[:-2])
                
                isValidDest = False
                dest = ""
                while(not isValidDest):
                    print("Select a Destination for removal")
                    dest = raw_input()
                    isValidDest = dest in airport.flights
                
                modNet.removeRoute(airport, dest)
                print("Route Removed!")
            elif(userIn =="3"):
                destination = raw_input("Destination Code: ")
                distance = int(raw_input("Distance: "))
                modNet.addRoute(airport, destination, distance)
                print("Route Added")
            elif(userIn =="4"):
                country = raw_input("New Country Value: ")
                modNet.changeCountry(airport, country)
                print("Changed Country!")
            elif(userIn =="5"):
                continent = raw_input("New Continent Value: ")
                modNet.changeContinent(airport, continent)
                print("Changed Continent!")
            elif(userIn =="6"):
                timezone = int(raw_input("New Timezone Value: "))
                modNet.changeTimezone(airport, timezone)
                print("Changed Timezone!")
            elif(userIn == "7"):
                region = int(raw_input("New Region Value: "))
                modNet.changeRegion(airport, region)
                print("Changed Region!")
            elif(userIn == "8"):
                population = int(raw_input("New Population Value: "))
                modNet.changePopulation(airport, population)
                print("Changed Population!")
            elif(userIn == "q"):
                break
            else:
                print("Sorry that was not valid input...")
            
def printModNetworkMenu():
    print("Please Select a choice or press q to quit:")
    print("1 - Remove City")
    print("2 - Remove a Route")
    print("3 - Add a Route")
    print("4 - Modify Country")
    print("5 - Modify Continent")
    print("6 - Modify Timezone")
    print("7 - Modify Region")
    print("8 - Modify Population")
    
def query(airports, translator):
    query = Queries.Queries(airports, translator)
    while(True):
        print("City List:")
        printCityList(query.getCityList())
        isValidCity = False
        while(not isValidCity):
            print("Select a city for querying on or q to go back:")
            city = raw_input()
            isValidCity = city in translator
            if(city == "q"):
                return
        
        
        while(True):
            printQueryMenu()
            input = raw_input()
            if(input == "1"):
                print(query.getCode(city))
            elif(input =="2"):
                print(query.getCountry(city))
            elif(input == "3"):
                print(query.getContinent(city))
            elif(input =="4"):
                print(query.getTimeZone(city))
            elif(input =="5"):
                print(query.getCoordinates(city))
            elif(input =="6"):
                print(query.getPopulation(city))
            elif(input =="7"):
                print(query.getRegion(city))
            elif(input == "8"):
                print(query.getCityFlights(city))
            elif(input == "q"):
                break
            else:
                print("Sorry that was not valid input...")

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
    
'''
This function handles all the Statstistics Operations
'''            
def stats(airports, translator):
    stats = Statistics.Statistics(airports, translator)
    while(True):
        printStatsMenu()
        input = raw_input()
        if(input == "1"):
            print(stats.getLongestFlight())
        elif(input =="2"):
            print(stats.getShortestFlight())
        elif(input =="3"):
            print(stats.getAverageFlightDistance())
        elif(input =="4"):
            print(stats.getLargestCity())
        elif(input =="5"):
            print(stats.getSmallestCity())
        elif(input =="6"):
            print(stats.getAverageCitySize())
        elif(input == "7"):
            print(stats.getContinentsList())
        elif(input == "8"):
            print(stats.getHubCity())
        elif(input == "q"):
            return
        
def drawMap(airports, translator):
    URL = "http://www.gcmap.com/mapui?P="
    for airport in airports.itervalues():
        currentCode = airport.code
        for destCode in airport.flights.keys():
            URL += (currentCode + "-" + destCode + ",+")
    return URL
    
def printStatsMenu():
    print("Please Select a choice or press q to quit:")
    print("1 - Longest Flight Length")
    print("2 - Shortest Flight Length")
    print("3 - Average Flight Length")
    print("4 - Largest City Population")
    print("5 - Smallest City Population")
    print("6 - Average Population Size")
    print("7 - Continent and City List")
    print("8 - Hub City")
    
def printQueryMenu():
    print("Please Select a choice or press q to quit:")
    print("1 - Code")
    print("2 - Country")
    print("3 - Continent")
    print("4 - Timezone")
    print("5 - Coordinates")
    print("6 - Population")
    print("7 - Region")
    print("8 - Flights")

def printMenu():
    print("Please Select a choice or press q to quit:")
    print("1 - Query on a City")
    print("2 - Query on the CS Air Network")
    print("3 - See the Route Map")
    print("4 - Edit the Network")
    print("5 - Learn about a Route")
    print("6 - Parse a File and Add to the Network")
    print("7 - Save Network to a File")
    
    
    
if __name__ == '__main__':
    main()