
public class Car {
	private final int moveH;
	private int xPos;
	private int yPos;
	private int speed;
	private int accelerate;
	private int hasMove;
	private final int width;
	private final int length;
	private int topSpeed;
	private int topSpeedWhenDeflated;
	private boolean isLightOn;
	private int lightIntensity;
	private Machine machine;
	private Tire tire;
	
	public Car()
	{
		moveH = 120;
		speed = 0;
		accelerate = 1;
		hasMove = 0;
		width = 60;
		length = 120;
		topSpeed = 250;
		topSpeedWhenDeflated = 30;
		isLightOn = false;
		lightIntensity = 0;
		machine = new Machine();
		tire = new Tire();
	}
	public Car(int x, int y)
	{
		xPos = x;
		yPos = y;
		moveH = 120;
		speed = 0;
		accelerate = 1;
		hasMove = 0;
		width = 60;
		length = 120;
		topSpeed = 250;
		topSpeedWhenDeflated = 30;
		isLightOn = false;
		lightIntensity = 0;
		machine = new Machine();
		tire = new Tire();
	}

	public int getMoveH()
	{
		return moveH;
	}
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getTopSpeed()
	{
		return topSpeed;
	}
	public int getWidth()
	{
		return width;
	}
	public int getLength()
	{
		return length;
	}
	public boolean getIsLightOn()
	{
		return isLightOn;
	}
	public int getLightIntensity()
	{
		return lightIntensity;
	}
	public int getMachineTemperature()
	{
		return machine.getTemperature();
	}
	public int getTirePressure()
	{
		return tire.getPressure();
	}
	
	public void setXPos(int xPos)
	{
		this.xPos = xPos;
	}
	public void setYPos(int yPos)
	{
		this.yPos = yPos;
	}
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	public void setIsLightOn(boolean state)
	{
		isLightOn = state;
	}
	public void setLightIntensity()
	{
		if(isLightOn)lightIntensity = 100;
		else lightIntensity = 0;
	}
	public void setTopSpeed(int topSpeed)
	{
		this.topSpeed = topSpeed;
	}
	public void forward(SpeedSensor speedSensor)
	{
		if(!tire.getIsVeryDeflated())
		{
			if(speed < topSpeed)
			{
				if(speed < speedSensor.getSafeSpeed()) 
				{
						speed = speed + accelerate;
				}
			}
		}
		else
		{
			if(speed < topSpeedWhenDeflated)
			{
				if(speed < speedSensor.getSafeSpeed()) 
				{
						speed = speed + accelerate;
						if(tire.getPressure() < 10) speed = 0;
				}
			}
			else
			{
				while(speed > topSpeedWhenDeflated)
				{
					brake();
				}
			}
		}
		yPos = yPos - speed;
		if(tire.getPressure() >= 10) machine.heatUp();
		tire.deflated();
	}
	public void brake()
	{
		if(speed > 0) speed = speed - 3*accelerate;
		yPos = yPos - speed;
		machine.coolDown();
		tire.deflated();
	}
	public void moveLeft()
	{
		while(hasMove < moveH)
		{
			xPos--;
			hasMove++;
		}
		hasMove = 0;
		tire.deflated();
	}
	public void moveRight()
	{
		while(hasMove < moveH)
		{
			xPos++;
			hasMove++;
		}
		hasMove = 0;
		tire.deflated();
	}
}
