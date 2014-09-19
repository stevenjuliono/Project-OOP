import java.util.Random;


public class ObstacleSensor extends Sensor{
	
	private final int sensorRadius;
	private boolean thereIsAnObstacle;
	private int carLinePosition;
	private Random randomForChooseLine;
	private int randomNumberForChooseLine;
	private int safeLine;
	private int minimumDistanceToMove;
	private int minimumSpeedToMove;

	
	public ObstacleSensor(Car car) {
		super(car);
		 sensorRadius = 100;
		 carLinePosition = 0;
		 randomForChooseLine = new Random();
		 minimumDistanceToMove = 8;
		 minimumSpeedToMove = 10;
	}

	public boolean getThereIsAnObstacle()
	{
		return thereIsAnObstacle;
	}
	
	public void detectObject(Car car, Road road, Obstacle obstacle)
	{
		if (car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) > 0)
		{
			if(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= sensorRadius) 
			{
				for(int i = 0; i<obstacle.getIsThereAnObstacle().length; i++)
				{
					if(car.getXPos() == road.getLine()[i] && obstacle.getIsThereAnObstacle()[i])
					{
						thereIsAnObstacle = true;
						carLinePosition = i;
					}
				}
			}
		}
		else thereIsAnObstacle = false;
 	}
	
	public void findNearSafeRoute(Car car,Obstacle obstacle)
	{
		randomNumberForChooseLine = randomForChooseLine.nextInt(10) + 1;
		while(Math.abs(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle())) < sensorRadius && car.getSpeed() > minimumSpeedToMove)
		{
			car.brake();
		}
		if(carLinePosition == 0) 
		{
			while(obstacle.getIsThereAnObstacle()[carLinePosition] && car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove)
			{
				car.moveRight();
				if(carLinePosition < obstacle.getIsThereAnObstacle().length)carLinePosition++;
			}
		}
		else if(carLinePosition == obstacle.getIsThereAnObstacle().length-1)
		{
			while(obstacle.getIsThereAnObstacle()[carLinePosition] && car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove)
			{
				car.moveLeft();
				if(carLinePosition > 0) carLinePosition--;
			}
		}
		else
		{
			safeLine = obstacle.getIsThereAnObstacle().length-2;
			for(int i = 1; i <= obstacle.getIsThereAnObstacle().length-2; i++)
			{
				if(i != carLinePosition)
				{
					if(Math.abs(carLinePosition-i) < safeLine)
						safeLine = i;
				}
			}
			if(obstacle.getIsThereAnObstacle()[carLinePosition-safeLine] && !obstacle.getIsThereAnObstacle()[carLinePosition+safeLine]) 
			{
				if(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove)car.moveRight();
				if(carLinePosition < obstacle.getIsThereAnObstacle().length)carLinePosition++;
				
			}
			else if(!obstacle.getIsThereAnObstacle()[carLinePosition-safeLine] && obstacle.getIsThereAnObstacle()[carLinePosition+safeLine]) 
			{
				if(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove) car.moveLeft();
				if(carLinePosition > 0 )carLinePosition--;
			}
			else
			{
				if(randomNumberForChooseLine < 6)
				{
					if(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove) car.moveLeft();
					if(carLinePosition > 0)carLinePosition--;
				}
				else
				{
					if(car.getYPos()-(obstacle.getYPos()+obstacle.getSizeOfObstacle()) <= minimumDistanceToMove)car.moveRight();
					if(carLinePosition < obstacle.getIsThereAnObstacle().length)carLinePosition++;
				}
			}
		}
		thereIsAnObstacle = false;
	}

}
