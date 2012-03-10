package mainCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
	This program tries to find a path between nodes depending on a value by using AStar pathfinding algorithm
    Copyright (C) 2012 João Serra email: bio_matrix@netvisao.pt

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class AStar {
	 // Contains all the cities TODO: Change this data Structure to a more simple
	public HashMap<String,Location> cities; 
	// List of paths currently being searched
	public HashMap<Double, Path> myPaths; // Cost, paths
	// List of bus stops
	public HashMap<String, Location[]> possiblePaths; // <Bus Name, Path to follow>
	public Boolean calcByDistance;
	public ArrayList<Road> roads;
	
	final static int earthRadius = 6371;
	
	/**
	 * Constructor
	 */
	public AStar()
	{
		this.cities = new HashMap<String,Location>();
		this.myPaths = new HashMap<Double, Path>();
		this.possiblePaths = new HashMap<String, Location[]>();
		this.calcByDistance = false; // By omission we will use distance as main factor to find the path
		this.roads = new ArrayList<Road>();
	}
	
	/**
	 * Import locations
	 * TODO: create method to add location by input and send this information to test
	 */
	public void addLocation(String name, Location city)
	{
		cities.put(name,city);
	}
	
	/**
	 * returns a location
	 * @param name
	 * @return
	 */
	public Location getLocation(String name)
	{
		return cities.get(name);
	}
	
	/**
	 * Add the roads
	 */
	public void addPath(String name, Location[] road)
	{
		//this.possiblePaths.add(road);
		this.possiblePaths.put(name, road);
	}
	
	/**
	 * Returns the result of the paths calculated
	 * @return
	 */
	public HashMap<String, Location[]> getPossiblePaths() {
		return this.possiblePaths;
	}
	
	/**
	 * Find distance between two cities
	 * Using Haversine formula
	 */
	public double calcDistance(String city1, String city2)
	{
		Location startPoint = cities.get(city1);
		Location endPoint = cities.get(city2);	
		
		
		// TODO: verify the data type
	 	double difLat = endPoint.getLatitude().getRads() - startPoint.getLatitude().getRads();
	 	double difLon = endPoint.getLongitude().getRads() - startPoint.getLongitude().getRads();

	 	double varA = Math.pow(Math.sin(difLat/2),2) + 
			   Math.cos(startPoint.getLatitude().getRads())*
			   Math.cos(endPoint.getLatitude().getRads())*
			   Math.pow(Math.sin(difLon/2),2);
		
	 	double varC = 2 * Math.atan2(Math.sqrt(varA), Math.sqrt(1-varA));

		double distance = earthRadius * varC; 
		return distance;
		
	}
	
	/**
	 * Calculates the distance between two points x and y ( latitude, longivity)
	 * Using decimal degrees
	 * @param x
	 * @param y
	 * @return
	 */
	public double calcDistance(Location startPoint, Location endPoint)
	{
		double distance = Math.sqrt(
				Math.pow((endPoint.getLatitude().getDecimalDegrees() - startPoint.getLatitude().getDecimalDegrees()),2) + 
				Math.pow((endPoint.getLongitude().getDecimalDegrees() - startPoint.getLongitude().getDecimalDegrees()),2)
			);
		return distance;
	}

	/**
	* Finds the best path between two cities using the A star algorithm
	* city1 - start 
	* city2 - destination
	*/
	public Path findPath(String city1, String destination)
	{
		// Initializing variables
		ArrayList<String> previousPath, childsNodes;
		String nextStop, parentNode;
		boolean foundCity = false;
		this.myPaths.clear();
		
		while ( this.myPaths.isEmpty() || // we don't have paths
				(!this.myPaths.get((Double)myPaths.keySet().toArray()[0]).getCities().contains(destination)) // first node don't reach the destiny 
				) 
		{
			double cost = 0; 
			double auxCost = 0;
			
			ArrayList<String> auxOldBusList = new ArrayList<String>();
			ArrayList<String> auxOldChangeBusList = new ArrayList<String>();
			Path auxPath;
			
			// Starting for first time
			if ( this.myPaths.isEmpty() ) 
			{
				previousPath = new ArrayList<String>(); // if there isn't a previous path create one
				parentNode = city1; // First parent is city 1
				previousPath.add(city1);  // add the parent to the path
			}
			else 
			{
				auxPath = myPaths.get(myPaths.keySet().toArray()[0]);
				previousPath = auxPath.getCities(); // work always with the lower cost object
				parentNode = previousPath.get(previousPath.size()-1); // keeps going from the last city of the best path ( with lower cost )				
				cost = (Double)myPaths.keySet().toArray()[0]; // cost until now, this is the first key of hashmap
				
				cost -= removeCost(parentNode, destination);// remove the last estimated cost
				
				auxOldBusList = auxPath.getBusList(); // Get the old list of buses before delete
				auxOldChangeBusList = auxPath.getChangeBusStops();
				myPaths.remove(myPaths.keySet().toArray()[0]); // delete the parent
			}	
			auxCost = cost; // Saving parent cost
			
			// Its possible to go to these cities
			childsNodes = checkPossibleConnections(parentNode); 
			
			if ( !checkChildsDepleted(childsNodes, previousPath) )
			{
				// Calculating cost from each child
				for (int i = 0; i < childsNodes.size(); i++)
				{
					cost = auxCost; // reset cost to parent cost
					nextStop = childsNodes.get(i);
					if (!previousPath.contains(nextStop)) // If the city is not in the path yet, this is to avoid going to a previous city
					{
						addCalculatedPath(cost,parentNode,nextStop,destination,previousPath,auxOldBusList, auxOldChangeBusList);
	
						// found the result, finish calculating paths for childs and get out of the loop
						/*if ( nextStop.equals(destination) )
						{
							foundCity = true;
						}*/
					}
				}
			}

			// Sort our HashMap
			sortHashMap();
		}
		
		return this.myPaths.get((Double)myPaths.keySet().toArray()[0]); // Cities of the first Path
		
	}
	
	/**
	 * Check if a path went to a dead end
	 * @param childNodes
	 * @param previousPath
	 * @return
	 */
	private boolean checkChildsDepleted(ArrayList<String> childNodes, ArrayList<String> previousPath)
	{
		for (String child : childNodes)
		{
			if ( !previousPath.contains(child) )
			{
				return false; // There's at least one child at which the path didn't go
			}
		}
		return true; // all childs are in the path, dead end
	}
	
	
	/**
	 * @param cost
	 * @param parentNode
	 * @param nextStop
	 * @param destination
	 * @param previousPath
	 * @param auxOldBusList
	 * @param auxOldChangeBusList
	 */
	private void addCalculatedPath(double cost, String parentNode, String nextStop, String destination,
			ArrayList<String> previousPath,ArrayList<String> auxOldBusList, ArrayList<String> auxOldChangeBusList)
	{
		ArrayList<String> newPath = new ArrayList<String>();
		
		cost += getCost(parentNode, nextStop, destination);
		
		newPath = copyList(previousPath); // Create a new path from the previous one 
		newPath.add(nextStop); // add the nextStop to the path
		
		Path pathToAdd = new Path(cost, newPath);
		
		for (int i = 0; i < auxOldBusList.size(); i++ )
		{
			pathToAdd.addBus(auxOldBusList.get(i), auxOldChangeBusList.get(i));
		}
		
		pathToAdd.addBus( // Add the new bus to the path
				getBusName(parentNode, nextStop), parentNode); // find the bus which is going to nextStop
		
		this.myPaths.put(cost, pathToAdd);// add the new Path
	}
	
	/**
	 * @param parent
	 * @param nextStop
	 * @param destination
	 * @return
	 */
	private double getCost(String parent, String nextStop, String destination)
	{
		double cost = 0.0;
		if ( calcByDistance )
		{
			cost += this.calcDistance(parent, nextStop); // add cost from the new path				
			cost += this.calcDistance(nextStop, destination); // add plus estimated cost
		}
		else // we are using time instead distance
		{
			cost += this.calcTime(parent, nextStop); // add cost from the new path				
			cost = cost + this.calcTime(nextStop, destination); // add plus estimated cost
		}
		return cost;
	}
	
	/**
	 * This method removes the estimated cost
	 * @param parentNode
	 * @param destination
	 * @return
	 */
	private double removeCost(String parentNode, String destination)
	{
		if ( calcByDistance )
			return this.calcDistance(parentNode, destination);
		else // we are using time instead distance
			return this.calcTime(parentNode, destination);
	}
	
	public double calcTime(String city1, String city2)
	{
		double cost;
		double maxSpeed = (Double)getRoadMaxSpeed(city1, city2);
		double distance = calcDistance(city1, city2);
		// We are trying to get speed from road from and to the same city
		if ( maxSpeed == 0 ) 
		{
			return 0.0; // cost to get to the city where we already are
		}
		else
		{
			cost = distance / maxSpeed ;
		}
		return cost;
	}
	
	/**
	 * Returns the max speed for a road between two cities
	 * @param city1
	 * @param city2
	 * @return
	 */
	public double getRoadMaxSpeed(String city1, String city2)
	{
		// Search in all our road lists
		for ( Road myRoad : this.roads )
		{
			// Search for the road we want
			if ( myRoad.getStartCity().equals(city1) && myRoad.getEndCity().equals(city2) )
			{
				return myRoad.getMaxSpeed();
			}
		}
		// TODO: replace for exceptions
		return 0.0; // Error, information not available
	}
	
	
	//TODO: implement this function which allow to reduce the number buses to catch
	//private void minimizeBus()
	
	/**
	 * Sorting HashMap
	 * @param input
	 * @return
	 */
	private void sortHashMap()
	{
	    Map<Double, Path> tempMap = new HashMap<Double, Path>();
	    for (Double wsState : myPaths.keySet()){
	        tempMap.put(wsState,myPaths.get(wsState));
	    }

	    List<Double> mapKeys = new ArrayList<Double>(tempMap.keySet());
	    HashMap<Double, Path> sortedMap = new LinkedHashMap<Double, Path>();
	    
	    TreeSet<Double> sortedSet = new TreeSet<Double>(mapKeys);
	    Object[] sortedArray = sortedSet.toArray();
	    int size = sortedArray.length;
	    for (int i=0; i<size; i++){
	    	//Path myPath = mapKeys.get(mapValues.indexOf(sortedArray[i]));
	    	Path myPath = myPaths.get(sortedArray[i]);
	        sortedMap.put((Double)sortedArray[i], myPath
	                );
	    }
	    this.myPaths = sortedMap;
	}
	
	/**
	 * Copy an arraylist into a new one
	 * @param myArray
	 * @return
	 */
	public ArrayList<String> copyList(ArrayList<String> myArray)
	{
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < myArray.size() ; i++)
		{
			temp.add(myArray.get(i)); 
		}
		return temp;
	}
	
	
	/**
	 * @param city1
	 * @param city2
	 * @return
	 */
	public String getBusName(String city1, String city2)
	{

		String currentCity; //aux variables

	    Set keys = possiblePaths.keySet();
	    // checks all possible paths for a match with the city
	    for (Iterator i = keys.iterator(); i.hasNext();) { 
	      String key = (String) i.next();
	      Location[] temp = possiblePaths.get(key); // pickup a path
			
			for (int j = 0; j < temp.length ; j++) // goes by the path
			{
				currentCity = temp[j].getName();
				if ( currentCity.equals(city1)) // found our city in the path
				{
					// Found a bus going from city 2 to city 1
					if ( ( (j-1) >= 0 ) && ( temp[j-1].getName().equals(city2))) // there is a stop before
					{
						return key;
					}
					// Found a bus going from city 1 to city 2
					if ( ((j+1) < temp.length) && ( temp[j+1].getName().equals(city2))) // there is a next stop
					{
						return key;
					}
				}
				// Break is not to be added since the bus can turn back, pass by the same city, and goes to a different city than before
			}
		}
		return "No bus";
	}
	
	/**
	 * Creates an array containing all the possible nodes that node can reach NEXT!
	 * @param myLocation
	 */
	public ArrayList<String> checkPossibleConnections(String city)
	{
		ArrayList<String> result = new ArrayList<String>();
		String currentCity = "", stopToBeAdded = ""; //aux variables
		Set keys = possiblePaths.keySet();
	    // checks all possible paths for a match with the city
	    for (Iterator i = keys.iterator(); i.hasNext();) { 
	      String key = (String) i.next(); // get the key with which we are working
	      Location[] temp = possiblePaths.get(key); // pickup a path
			
			for (int j = 0; j < temp.length ; j++) // goes by the path
			{
				currentCity = temp[j].getName();
				if ( currentCity.equals(city)) // found our city in the path
				{
					if ( (j-1) >= 0  ) // there is a stop before
					{
						stopToBeAdded = temp[j-1].getName();
						if ( !result.contains(stopToBeAdded))
							result.add(stopToBeAdded); // add previous stop
					}
					
					if ( (j+1) < temp.length ) // there is a next stop
					{
						stopToBeAdded = temp[j+1].getName();					
						if ( !result.contains(stopToBeAdded))
							result.add(stopToBeAdded); // add next stop
					}
						
				}
				// Break is not to be added since the bus can turn back, pass by the same city, and goes to a different city than before
			}
		}
		
		// return the result in array
		return result;
	}
	
	/**
	 * Create and add a new road to our road list
	 * @param startCity
	 * @param endCity
	 * @param maxSpeed
	 */
	public void addRoad(String startCity, String endCity, double maxSpeed)
	{
		this.roads.add(new Road(startCity, endCity, maxSpeed));
	}
	
	/**
	 * Find if a city has a neighbor and return its position, if not returns -1
	 * @param neighbors
	 * @param name
	 * @return
	 */
	public static int searchNeighbor(String[] neighbors, String name)
	{
		for (int n = 0; n < neighbors.length; n++)
		{
			if (neighbors[n].equals(name))
			{
				return n;
			}
		}
		return -1;

	}
	
	/**
	 * Checks if a city is in the bus stop list and returns its position
	 * @return
	 */
	public int searchBusStop(Location[] path, String name)
	{
		for ( int i = 0; i < path.length; i++)
		{
			if ( path[i].getName().equals(name))
			{
				return i; // returns the position of the stop
			}
		}
		System.out.println("ERROR at searchBusStop method");
		return -1; // couldn't find the stop
	}

	public void setCalcByDistance(Boolean calcByDistance) {
		this.calcByDistance = calcByDistance;
	}
	
 
	
}
