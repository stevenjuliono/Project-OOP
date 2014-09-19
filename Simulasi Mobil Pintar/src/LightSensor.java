
public class LightSensor implements SensorBehaviour {
	
	private Car car;
	public LightSensor(Car car)
	{
		this.car = car;
	}
	public int display() {
		return car.getLightIntensity();
	}
	public boolean getLightStatus(){
		return car.getIsLightOn();
	}
	public void setLight(){
		car.setLightIntensity();
	}
	public void switchLight(boolean setLight)
	{
		car.setIsLightOn(setLight);
	}

}
