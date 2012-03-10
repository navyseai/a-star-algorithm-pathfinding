package mainCore;

import java.util.ArrayList;

public class Path {

	public ArrayList<String> cities;
	public ArrayList<String> buses;
	public ArrayList<String> changeBusStops;
	public double cost;
	
	public Path()
	{
		this.cost = 0.0;
		this.cities = new ArrayList<String>();
		this.buses = new ArrayList<String>();
		this.changeBusStops = new ArrayList<String>(); 
	}
	
	public Path(double cost, ArrayList<String> cities)
	{
		this.cost = cost;
		this.cities = cities;
		this.buses = new ArrayList<String>();
		this.changeBusStops = new ArrayList<String>();
	}

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void addBus(String busName, String city)
	{
		if ( !this.buses.contains(busName) )
		{
			this.buses.add(busName);
			setBusChangeCity(city);
		}
	}
	
	public ArrayList<String> getBusList()
	{
		return this.buses;
	}

	public void setBusChangeCity(String city)
	{
		this.changeBusStops.add(city);
	}

	public ArrayList<String> getChangeBusStops() {
		return changeBusStops;
	}
	
}
