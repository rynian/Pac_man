import java.awt.Color;
import java.awt.Graphics;


public class Maze
{
	int windowWidth;
	int windowHeight;
	int[][] level;
	int xSize;
	int ySize;
	int pelletCount;
	public Maze(int wid, int hei,int windowW, int windowH)
	{
		level = new int[wid][hei];
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/level.length;
		ySize = windowH/level[0].length;
	}
	
	public Maze(int[][] arr, int windowW, int windowH)
	{
		level = arr;
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/level.length;
		ySize = windowH/level[0].length;
	}
	
	public boolean wall(int x,int y)
	{
			if (level[x][y]==1)
				return true;
			else
				return false;
	}
	
	public void setLevel(int x, int y, int value)
	{
		level[x][y] = value;
		//Maze temp = new Maze(level, windowWidth, windowHeight);
		
		
	}
	public int[][] getLevel()
	{
		return level;
	}
	
	public int getCord(int x, int y)
	{
		return level[x][y];
	}
	
	 public boolean isGhost(int x, int y)
		{
			if(level[x][y]==5||level[x][y]==6||level[x][y]==7||level[x][y]==8)
				return true;
			else
				return false;
		}
	 
	 public boolean inBox(int x, int y)
	 {
		 if(x==17||x==18||x==19)
		 {
			 if(y==12||y==13||y==14||y==15||y==16||y==17)
				 return true;
		 }
		return false;
	 }
	 
	 public boolean isPel(int x, int y)
	 {
		 if(level[x][y]==3)
			 return true;
		 else
			 return false;
	 }
	
		public void draw(Graphics g)
		{
	  	pelletCount = 0;
			for(int i=0;i<level.length;i++)
			{
				for (int j=0;j<level[0].length;j++)
				{
					if(level[i][j]==1)
					{
						
						g.setColor(Color.BLUE);
						g.fillRect(i*xSize, j*ySize, xSize, ySize);
					}
					if(level[i][j]==0)
					{
						g.setColor(Color.WHITE);
						g.fillOval(i*xSize+(xSize/4), j*ySize+(ySize/4), xSize/2, ySize/2);
	          pelletCount++;
					}
					if(level[i][j]==4)
					{
						g.setColor(Color.BLACK);
						g.fillRect(i*xSize, j*ySize, xSize, ySize);
					}
					if(level[i][j]==3)
					{
						g.setColor(Color.CYAN);
						g.fillOval(i*xSize+(xSize/4), j*ySize+(ySize/4), xSize/2, ySize/2);
	          pelletCount++;
					}
//				if(level[i][j]==2)
//				{
//					g.setColor(Color.YELLOW);
//					g.fillOval(i*xSize, j*ySize, xSize, ySize);
//				}
//				if(level[i][j]==5)
//				{
//					g.setColor(Color.GREEN);
//					g.fillOval(i*xSize, j*ySize, xSize, ySize);
//				}
//				if(level[i][j]==6)
//				{
//					g.setColor(Color.PINK);
//					g.fillOval(i*xSize, j*ySize, xSize, ySize);
//				}
//				if(level[i][j]==7)
//				{
//					g.setColor(Color.ORANGE);
//					g.fillOval(i*xSize, j*ySize, xSize, ySize);
//				}
//				if(level[i][j]==8)
//				{
//					g.setColor(Color.RED);
//					g.fillOval(i*xSize, j*ySize, xSize, ySize);
//				}
			}
		}
	}
}