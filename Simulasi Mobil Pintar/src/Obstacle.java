import java.util.Random;

public class Obstacle {
	private int yPos;
	private final int sizeOfObstacle;
	private boolean isThereAnObstacle[];
	private final int maxObstacleInLine;
	private int obstacleInLine;
	private Random random;
	private int randomNumber;
	
	public Obstacle()
	{
		sizeOfObstacle = 60;
		isThereAnObstacle = new boolean[3];
		maxObstacleInLine = 2;
		obstacleInLine = 0;
		random = new Random();
	}
	public Obstacle(int posY)
	{
		this.yPos = posY;
		sizeOfObstacle = 60;
		isThereAnObstacle = new boolean[3];
		maxObstacleInLine = 2;
		obstacleInLine = 0;
		random = new Random();
	}
	
	public int getYPos()
	{
		return yPos;
	}
	public int getSizeOfObstacle()
	{
		return sizeOfObstacle;
	}
	public boolean[] getIsThereAnObstacle()
	{
		return isThereAnObstacle;
	}
	
	public void generateObstacle()
	{
		obstacleInLine = 0;
		for(int i=0; i<isThereAnObstacle.length;i++)
		{
			randomNumber = random.nextInt(10)+1;
			if(randomNumber < 6 && obstacleInLine < maxObstacleInLine) 
			{
				isThereAnObstacle[i] = true;
				obstacleInLine++;
			}
			else isThereAnObstacle[i] = false;
		}
	}
	public void cleanObject()
	{
		for(int i= 0; i < isThereAnObstacle.length; i++)
		{
			isThereAnObstacle[i] = false;
		}
	}
}
