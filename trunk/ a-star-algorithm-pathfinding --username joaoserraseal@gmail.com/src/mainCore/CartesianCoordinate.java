package mainCore;

public class CartesianCoordinate {

	private double degrees,minutes,seconds;
	
	public CartesianCoordinate()
	{
		degrees = 0.0;
		minutes = 0.0;
		seconds = 0.0;
	}
	
	public CartesianCoordinate(double degrees, double minutes, double seconds)
	{
		this.degrees = degrees;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	// Convert cartesian coordinates to decimal degrees
	public double getDecimalDegrees()
	{
		return this.degrees+(this.minutes*(1/60))+(this.seconds*(1/60)*(1/60));
	}
	
	public double getRads()
	{
		return getDecimalDegrees()*Math.PI/180;
	}
	
	public double getDegrees() {
		return degrees;
	}

	public void setDegrees(double degrees) {
		this.degrees = degrees;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}
}
