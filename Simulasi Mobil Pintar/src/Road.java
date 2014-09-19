import javax.swing.JFrame;

public class Road extends JFrame {
		private final int[] Line;
		
		public Road()
		{
			Line = new int[3];
			for(int i = 0; i<Line.length; i++)
			{
				Line[i] = i*120 + 210;
			}
		}
		public int[] getLine()
		{
			return Line;
		}
		
}
