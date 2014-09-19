
public class TireSensor extends Sensor implements SensorBehaviour {
	
	private int maxSpeedToMove;
	public TireSensor(Car car)
	{
		super(car);
		maxSpeedToMove = 20;
	}
	public int display() {
		return car.getTirePressure();
	}
}
