
public class MachineSensor extends Sensor implements SensorBehaviour 
{
	public MachineSensor(Car car) {
		super(car);
	}
	public int display() 
	{
		return car.getMachineTemperature();
	}
}
