import java.awt.Point;
import java.util.ArrayList;

public class Path {
	Point current;
	ArrayList<Point> previous;
	
	public Path(Point p){
		current = p;
		previous = new ArrayList<Point>();
	}
	
	
	public Path(Point p, ArrayList<Point> old)
	{
		current = p;
		previous = old;	
	}
	
	public ArrayList<Point> getList()
	{
		return previous;
	}
	
	public Point getPoint()
	{
		return current;
	}
	
	public Point getLastPoint()
	{
		return previous.get(previous.size()-1);
	}
	
	public void setCurrent(Point p)
	{
		current = p;
	}
	
	public void setPrevious(ArrayList<Point> old)
	{
		previous = old;
	}
	
	public void addPoint(Point p)
	{
		previous.add(p);
	}
}
