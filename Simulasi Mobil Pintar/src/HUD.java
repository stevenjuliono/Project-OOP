import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

class HUD extends JFrame implements Runnable
{
	private Image carImage;
	private Image roadImage;
	private Image lightImage;
	private Image obstacleImage;
	private int hudWidth;
	private int hudHeight;
	private int displayerWidth;
	private int displayerHeight;
	Thread t;
	Car car;
	Road road;
	Obstacle obstacle;
	Obstacle obstacle1;
	boolean night;
	String dayLight;
	Random randomDay;
	Integer dayNumber;
	SpeedSensor speedSensor;
	TireSensor tireSensor;
	LightSensor lightSensor;
	MachineSensor machineSensor;
	ObstacleSensor obstacleSensor;
	String lampStat;
	
	public HUD()
	{
		hudWidth = 540;
		hudHeight = 900;
		setTitle("Smart Car Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(hudWidth,hudHeight);
		setVisible(true);
		setResizable(false);
		displayerWidth = 180;
		displayerHeight = 90;
		t = new Thread(this);
		car = new Car(330,-150);
		road = new Road();
		obstacle = new Obstacle(hudHeight*2/3);
		obstacle1 = new Obstacle(hudHeight/3);
		night = true;
		randomDay = new Random();
		dayNumber = 0;
		speedSensor = new SpeedSensor(car);
		tireSensor = new TireSensor(car);
		lightSensor = new LightSensor(car);
		machineSensor = new MachineSensor(car);
		obstacleSensor = new ObstacleSensor(car);
		try
		{
			carImage = ImageIO.read(new File("src/car.png"));
			roadImage = ImageIO.read(new File("src/Road.jpg"));
			lightImage = ImageIO.read(new File("src/Light.png"));
			obstacleImage = ImageIO.read(new File("src/Stone.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		t.start();
	}
	public void paint(Graphics g)
	{
		try{
			g.setFont(new Font("Brittanic Bold", Font.BOLD, 17));
			g.drawImage(roadImage,displayerWidth,0,this);
			g.drawImage(carImage,car.getXPos(),car.getYPos(),this);
			for(int i = 0; i < obstacle.getIsThereAnObstacle().length; i++)
			{
				if(obstacle.getIsThereAnObstacle()[i])
					//g.fillRect(road.getLine()[i], obstacle.getYPos(), obstacle.getSizeOfObstacle(),obstacle.getSizeOfObstacle());
					g.drawImage(obstacleImage,road.getLine()[i],obstacle.getYPos(),this);
			}
			for(int i = 0; i < obstacle1.getIsThereAnObstacle().length; i++)
			{
				if(obstacle1.getIsThereAnObstacle()[i])
					//g.fillRect(road.getLine()[i], obstacle1.getYPos(), obstacle1.getSizeOfObstacle(),obstacle1.getSizeOfObstacle());
					g.drawImage(obstacleImage,road.getLine()[i],obstacle1.getYPos(),this);
			}
			if(lightSensor.getLightStatus())
				g.drawImage(lightImage,car.getXPos()+8,car.getYPos()-20,this);
			for(int i = 0; i<7; i++)
			{
				if(i%2 == 0) g.setColor(Color.GREEN);
				else g.setColor(Color.YELLOW);
				g.fillRect(0, i*displayerHeight+50, displayerWidth, displayerHeight);
			}
			g.setColor(Color.BLACK);
			g.drawString("Speed", 0*displayerWidth+10, 0*displayerHeight+80);
			g.drawString("Auto Lock Speed", 0*displayerWidth+10, 1*displayerHeight+80);
			g.drawString("Therm", 0*displayerWidth+10, 2*displayerHeight+80);
			g.drawString("Light Intensity", 0*displayerWidth+10, 3*displayerHeight+80);
			g.drawString("Tire Pressure", 0*displayerWidth+10, 4*displayerHeight+80);
			g.drawString("Day", 0*displayerWidth+10, 5*displayerHeight+80);
			g.drawString("Light Status", 0*displayerWidth+10, 6*displayerHeight+80);
			g.setFont(new Font("Brittanic Bold", Font.BOLD, 30));
			g.drawString(String.valueOf(speedSensor.display()) + " mps", 0*displayerWidth+10, 0*displayerHeight+110);
			g.drawString(speedSensor.autoLockSpeed(), 0*displayerWidth+10, 1*displayerHeight+110);
			g.drawString(String.valueOf(machineSensor.display()) + " K", 0*displayerWidth+10,2*displayerHeight+110);
			g.drawString(String.valueOf(lightSensor.display()) + " Cd", 0*displayerWidth+10, 3*displayerHeight+110);
			g.drawString(String.valueOf(tireSensor.display())+ " P", 0*displayerWidth+10, 4*displayerHeight+110);
			g.drawString(dayLight, 0*displayerWidth+10, 5*displayerHeight+110);
			g.drawString(lampStat, 0*displayerWidth+10, 6*displayerHeight+110);
		} catch(NullPointerException e){
			e.printStackTrace();
		}
		
	}
	public void run()
	{
		while(true)
		{
			try
			{
				
				if(car.getYPos() < -car.getLength()) 
				{
					obstacle.cleanObject();
					obstacle1.cleanObject();
					obstacle.generateObstacle();
					obstacle1.generateObstacle();
					dayNumber = randomDay.nextInt(100)+1;
					if(dayNumber < 50) night = false;
					else night = true;
					car.setYPos(hudHeight+(car.getLength()+20));
				}
				if(night) 
				{
					lampStat = "ON";
					dayLight = "NIGHT";
					lightSensor.switchLight(true);
					lightSensor.setLight();
				}
				else 
				{
					lampStat = "OFF";
					dayLight = "NOON";
					lightSensor.switchLight(false);
					lightSensor.setLight();
				}
				car.forward(speedSensor);
				obstacleSensor.detectObject(car, road, obstacle);
				if(obstacleSensor.getThereIsAnObstacle()) obstacleSensor.findNearSafeRoute(car, obstacle);
				
				obstacleSensor.detectObject(car, road, obstacle1);
				if(obstacleSensor.getThereIsAnObstacle()) obstacleSensor.findNearSafeRoute(car, obstacle1);
				repaint();
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
