import java.awt.Color;
import java.awt.Graphics;


public class pac 
{
	Color c;
	int x;
	int y;
	int windowW;
	int windowH;
	public pac(int xCoor, int yCoor, int w,  int h)
	{
		x = xCoor;
		y = yCoor;
		windowW = w;
		windowH = h;
	}
	
	public void setX(int c)
	{
		x = c;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int c)
	{
		y = c;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval(x*windowW, y*windowH, windowW, windowH);
	}
	
	
}
