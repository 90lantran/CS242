import Parse
import Airport
import Queries
import Statistics

'''
This file acts as a UI for the program. It starts in main and loops until 
the user is finished. It is responsible for calling the other Files as 
needed to return the results requested.

@author: Joshua Harris
'''

'''
This is the main function for the whole program and is incharge of
handling the user input
'''
def main():
    print("Welcome to my CS Air Module")
    print("By: Joshua Harris")
    print("------------------------")
    print("Parseing the JSON Text File")
    
    parser = Parse.Parser()
    parser.parse()
    
    print("Parse Complete!")
    print("Starting Normal Routine...\n")
    
    while(True):
        printMenu()
        input = raw_input()
        if(input == "1"):
            query(parser.airportList, parser.cityToCode)
        elif(input == "2"):
            stats(parser.airportList, parser.cityToCode)
        elif(input == "3"):
            print(drawMap(parser.airportList, parser.cityToCode))
        elif(input == "q"):
            break

'''
This manages the Query operations for the program
'''
def query(airports, translator):
    query = Queries.Queries(airports, translator)
    
    # Loop through the option until the user types 'q'
    while(True):
        # Print out a city list for the users to select from
        print("City List:")
        print(query.getCityList())
        
        print("Select a city for querying on or q to go back:")
        city = raw_input()
        if(city == "q"):
            return
        printQueryMenu()
        userInput = raw_input()
        if(userInput == "1"):
            print(query.getCode(city))
        elif(userInput == "2"):
            print(query.getCountry(city))
        elif(userInput == "3"):
            print(query.getContinent(city))
        elif(userInput == "4"):
            print(query.getTimeZone(city))
        elif(userInput == "5"):
            print(query.getCoordinates(city))
        elif(userInput == "6"):
            print(query.getPopulation(city))
        elif(userInput == "7"):
            print(query.getRegion(city))
        elif(userInput == "8"):
            print(query.getCityFlights(city))
        elif(userInput == "q"):
            return

'''
This is in charge of handling all the Statistic Operations
'''
def stats(airports, translator):
    stats = Statistics.Statistics(airports, translator)
    
    # Loop through the options until the user selects 'q'
    while(True):
        
        printStatsMenu()
        userInput = raw_input()
        
        if(userInput == "1"):
            print(stats.getLongestFlight())
        elif(userInput =="2"):
            print(stats.getShortestFlight())
        elif(userInput =="3"):
            print(stats.getAverageFlightDistance())
        elif(userInput =="4"):
            print(stats.getLargestCity())
        elif(userInput =="5"):
            print(stats.getSmallestCity())
        elif(userInput =="6"):
            print(stats.getAverageCitySize())
        elif(userInput == "7"):
            print(stats.getContinentsList())
        elif(userInput == "8"):
            print(stats.getHubCity())
        elif(userInput == "q"):
            return
        
'''
This is incharge of genreating the URL for the website to create the map
'''
def drawMap(airports, translator):
    URL = "http://www.gcmap.com/mapui?P="
    for airportKey in airports:
        airport = airports[airportKey]
        currentCode = airport.code
        for destCode in airport.flights.keys():
            URL += (currentCode + "-" + destCode + ",+")
    return URL[:-2]
    
'''
This prints a menu for the Statistic Choices
'''
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
    
'''
This prints a menu for the Queries Choices
'''
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

'''
This prints a menu for the main options
'''
def printMenu():
    print("Please Select a choice or press q to quit:")
    print("1 - Query on a City")
    print("2 - Query on the CS Air Network")
    print("3 - See the Route Map")
    
    
if __name__ == '__main__':
    main()