
public class Machine {
	private int temperature;
	private int deltaTemperature;
	private final int maxTemperature;
	private final int minTemperature;
	
	public Machine()
	{
		temperature = 500;
		deltaTemperature = 10;
		maxTemperature = 1500;
		minTemperature = -500;
	}
	
	public int getTemperature()
	{
		return temperature;
	}
	public void heatUp()
	{
		if(temperature + deltaTemperature <= maxTemperature) temperature = temperature + deltaTemperature;
	}
	public void coolDown()
	{
		if(temperature - deltaTemperature >= minTemperature) temperature = temperature - deltaTemperature;
	}
}
