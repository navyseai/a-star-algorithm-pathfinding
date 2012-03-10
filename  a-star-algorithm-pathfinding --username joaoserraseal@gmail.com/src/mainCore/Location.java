package mainCore;

public class Location {
	
	private CartesianCoordinate latitude,longitude;
	private String name;
	private String[] neighbors; // Neighbors from this location
	
	/**
	 * Constructor without input parameters
	 */
	public Location()
	{
		name = " ";
		latitude = new CartesianCoordinate();
		longitude = new CartesianCoordinate();
		neighbors = new String[0];
	}
	
	/**
	 * Constructor 1
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param neighbors
	 */
	public Location(String name, CartesianCoordinate latitude, CartesianCoordinate longitude, String[] neighbors)
	{
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.neighbors = neighbors;
	}
	
	/**
	 * Constructor 2
	 * @param name
	 * @param degrees
	 * @param minutes
	 * @param degrees2
	 * @param minutes2
	 * @param neighbors
	 */
	public Location(String name,
			double degrees, double minutes, // latitude
			double degrees2, double minutes2, // Longitude
			String[] neighbors) 
	{
		this.name = name;
		this.latitude = new CartesianCoordinate(degrees,minutes,0);
		this.longitude = new CartesianCoordinate(degrees2,minutes2,0);
		this.neighbors = neighbors;
	}
	
	/**
	 * Constructor 3
	 * @param name
	 * @param degrees
	 * @param minutes
	 * @param seconds
	 * @param degrees2
	 * @param minutes2
	 * @param seconds2
	 * @param neighbors
	 */
	public Location(String name,
			double degrees, double minutes, double seconds, // latitude
			double degrees2, double minutes2, double seconds2, // Longitude 
			String[] neighbors) 
	{
		this.name = name;
		this.latitude = new CartesianCoordinate(degrees,minutes,seconds);
		this.longitude = new CartesianCoordinate(degrees2,minutes2,seconds2);
		this.neighbors = neighbors;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CartesianCoordinate getLatitude() {
		return latitude;
	}

	public void setLatitude(CartesianCoordinate latitude) {
		this.latitude = latitude;
	}

	public CartesianCoordinate getLongitude() {
		return longitude;
	}

	public void setLongitude(CartesianCoordinate longitude) {
		this.longitude = longitude;
	}
	
	public String[] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(String[] neighbors) {
		this.neighbors = neighbors;
	}
}
