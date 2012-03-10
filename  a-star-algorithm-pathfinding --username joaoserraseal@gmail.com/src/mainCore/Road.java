package mainCore;

/**
 * This class saves information of a maximum speed between cities
 * @author Joao Serra
 *
 */
public class Road {
	
	String startCity;
	String endCity;
	double maxSpeed;
	
	public Road(String startCity, String endCity)
	{
		this.startCity = startCity;
		this.endCity = endCity;
		this.maxSpeed = 0;
	}
	
	public Road(String startCity, String endCity, double maxSpeed)
	{
		this.startCity = startCity;
		this.endCity = endCity;
		this.maxSpeed = maxSpeed;
	}
	
	public String getStartCity() {
		return startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}
}
