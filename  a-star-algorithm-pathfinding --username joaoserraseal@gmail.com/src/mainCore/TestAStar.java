package mainCore;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
This program tries to find a path between nodes depending on a value by using AStar pathfinding algorithm
this is a test class for the program
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
public class TestAStar extends TestCase {

	public AStar algoritmo = new AStar();
	//public ArrayList<String[]> possiblePaths;
	//public ArrayList<HashMap<Double,String[]>> possiblePaths;

	/**
	*  Check the values of distances between cities (km's)
	 * WARNING! these are strait lines
	 * they do not include mountains or any kind of height curves
	 */
	public void testDistance() {
		addCities();
		assertEquals(344, Math.round(this.algoritmo.calcDistance("Porto", "Beja"))); 
		assertEquals(239, Math.round(this.algoritmo.calcDistance("Aveiro", "Beja")));
		assertEquals(111, Math.round(this.algoritmo.calcDistance("Aveiro", "Porto")));
		assertEquals(344, Math.round(this.algoritmo.calcDistance("Lisboa", "Porto")));
		assertEquals(239, Math.round(this.algoritmo.calcDistance("Lisboa", "Aveiro")));
		assertEquals(175, Math.round(this.algoritmo.calcDistance("Lisboa", "Beja")));
	}
	
	/**
	 * Test the connections between the nodes by using the buses paths
	 */
	public void testConnections(){
		addCities();
		addPaths();
		ArrayList<String> bejaConnections = this.algoritmo.checkPossibleConnections("Beja");
		assertEquals("Lisboa", bejaConnections.get(0));
		
		ArrayList<String> lisboaConnections = this.algoritmo.checkPossibleConnections("Lisboa");
		assertEquals("Beja", lisboaConnections.get(0));
		assertEquals("Aveiro", lisboaConnections.get(1));
		assertEquals("Porto", lisboaConnections.get(2));
		
		ArrayList<String> aveiroConnections = this.algoritmo.checkPossibleConnections("Aveiro");
		assertEquals("Lisboa", aveiroConnections.get(0));
	}
	
	
	/**
	 * Search for buses which stop in Beja and return their position in possiblePaths
	 */
/*	public void testSearchBus()
	{
		addCities();
		addPaths();
		ArrayList<Integer> busStopsBeja = this.algoritmo.findBus("Beja");
		// There are 2 buses for beja
		assertEquals(new Integer(0), busStopsBeja.get(0));
		assertEquals(new Integer(1), busStopsBeja.get(1));
	}
	
	*//**
	 * Search for buses which stop in Aveiro and return their position in possiblePaths
	 *//*
	public void testSearchBus2()
	{
		addCities();
		addPaths();
		ArrayList<Integer> busStopsAveiro = this.algoritmo.findBus("Aveiro");
		// There are 2 buses for Aveiro
		assertEquals(new Integer(0), busStopsAveiro.get(0));
		assertEquals(new Integer(2), busStopsAveiro.get(1));
	}*/
	
	/**
	 * Small test to check AStar is working
	 * First search
	 */
	public void testCheckResultPaths()
	{
		addCities();
		addPaths();
		System.out.println(this.algoritmo.findPath("Beja", "Porto").toString());
	}
		
	// Add the possible paths
	public void addPaths()
	{
		//TODO: Change this array to String with names of the citys
		// Adds the first path
		Location[] road1 = new Location[3];
		road1[0] = this.algoritmo.getLocation("Beja");
		road1[1] = this.algoritmo.getLocation("Lisboa");
		road1[2] = this.algoritmo.getLocation("Aveiro");
		this.algoritmo.addPath("bus1", road1);
		
		// Adds the second path
		Location[] road2 = new Location[3];
		road2[0] = this.algoritmo.getLocation("Porto");
		road2[1] = this.algoritmo.getLocation("Lisboa");
		road2[2] = this.algoritmo.getLocation("Beja");
		this.algoritmo.addPath("bus2",road2);
		
		// Adds the third path
		Location[] road3 = new Location[3];
		road3[0] = this.algoritmo.getLocation("Porto");
		road3[1] = this.algoritmo.getLocation("Lisboa");
		road3[2] = this.algoritmo.getLocation("Aveiro");
		this.algoritmo.addPath("bus3",road3);

		// Values of path can be calculated using an already made function to calc the distance between two cities
	}
	
	/**
	*  Add cities
	*/
	public void addCities()
	{
		// Associate the neighbors to create the graph
		String[] neighborsBeja = new String[2];
		neighborsBeja[0] = "Lisboa";
		neighborsBeja[1] = "Porto";
		
		String[] neighborsPorto = new String[2];
		neighborsPorto[0] = "Aveiro";
		neighborsPorto[1] = "Lisboa";
		
		String[] neighborsAveiro = new String[2];
		neighborsAveiro[0] = "Porto";
		neighborsAveiro[1] = "Lisboa";
		
		String[] neighborsLisboa = new String[2];
		neighborsLisboa[0] = "Aveiro";
		neighborsLisboa[1] = "Beja";
		
		String[] neighborsPortalegre = new String[4];
		neighborsPortalegre[0] = "Aveiro";
		neighborsPortalegre[1] = "Beja";
		neighborsPortalegre[2] = "Porto";
		neighborsPortalegre[3] = "Lisboa";
		
		//Coordinates obtained via google earth
		Location beja = new Location("Beja",
				38.0, 0.0, 55.0, //latitude
				7.0, 51.0, 54.0, //longitude
				neighborsBeja);
		
		Location porto = new Location("Porto",
				41.0, 8.0, 59.0, //latitude
				8.0, 36.0, 36.0, //longitude
				neighborsPorto);
		
		Location aveiro = new Location("Aveiro",
				40.0, 38.0, 28.0, //latitude
				8.0, 39.0, 13.0, //longitude
				neighborsPorto);
		
		Location lisboa = new Location("Lisboa",
				38.0, 42.0, 25.0, //latitude
				9.0, 8.0, 7.0, //longitude
				neighborsLisboa);
		
		Location portalegre = new Location("Portalegre",
				39.0,17.0,29.10, // latitude
				7.0,25.0,56.54, // longitude
				neighborsPortalegre);
		
		this.algoritmo.addLocation(beja.getName(),beja);
		this.algoritmo.addLocation(porto.getName(),porto);
		this.algoritmo.addLocation(aveiro.getName(),aveiro);
		this.algoritmo.addLocation(lisboa.getName(),lisboa);
		this.algoritmo.addLocation(portalegre.getName(),portalegre);
	}
}
