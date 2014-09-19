
public class Tire {
	private int pressure;
	private boolean isVeryDeflated;
	
	public Tire()
	{
		pressure = 1100;
		isVeryDeflated = false;
	}
	
	public int getPressure()
	{
		return pressure;
	}
	public boolean getIsVeryDeflated()
	{
		return isVeryDeflated;
	}
	public void deflated()
	{
		if(pressure > 0) pressure--;
		if(pressure < 1000) isVeryDeflated = true;
	}
}
