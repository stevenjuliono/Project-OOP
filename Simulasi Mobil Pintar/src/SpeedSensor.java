
public class SpeedSensor extends Sensor implements SensorBehaviour 
{
	private final int safeSpeed;
	public SpeedSensor(Car car)
	{
		super(car);
		safeSpeed = 50;
	}
	public int display() 
	{
		return car.getSpeed();
	}
	public int getSafeSpeed()
	{
		return safeSpeed;
	}
	public String autoLockSpeed()
	{
		if(car.getSpeed() == safeSpeed) return "ON";
		else return "OFF";
	}
}
