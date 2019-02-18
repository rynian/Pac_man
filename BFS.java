import java.awt.Point;
import java.util.ArrayList;

public class BFS {
	public ArrayList<Path> paths;
	public Point start;
	public Point end;
	public int[][] g1;
	public boolean foundPac = false;
	public int xSize;
	public int ySize;
	public BFS(Point s, Point e, int[][] maze)
	{
		start = s;
		end = e;
		xSize = maze.length;
		ySize = maze[0].length;
		paths = new ArrayList<Path>();
		g1 = maze;
	}
	
	
	public Point run()
	{
		Path first = new Path(start);
		paths.add(first);
		Point found = bestPath().get(1);
		return found;
	}
	
	public ArrayList<Point> bestPath()
	{		
		int count = 0;
		while(!foundPac)
		{
			
			System.out.println(count);
			count++;
			System.out.println("before size: " + paths.size());
			checkNext(paths.get(0));
			System.out.println("after size: " + paths.size());
			paths.remove(0);
			
		}
		return paths.get(paths.size() -1).getList();
	}
	
	public void checkNext(Path pa)
	{
		Point temp;
		
		for(int i = 0; i < 4; i++)
		{
			if(i == 0 && canMoveLeft((int)pa.getPoint().getX() - 1, (int)pa.getPoint().getY()))
			{
				temp = new Point();
				temp.setLocation((int)pa.getPoint().getX() - 1, (int)pa.getPoint().getY());
				if(!isVisited(temp, pa.getLastPoint()))
				{
					Path createpath = new Path(pa.getPoint(), pa.getList());
					createpath.addPoint(pa.getPoint());
					createpath.setCurrent(temp);
					paths.add(createpath);
					if(paths.get(paths.size() -1).equals(end))
					{
						foundPac = true;
						break;
					}
				}
			}
			if(i == 1 && canMoveRight((int)pa.getPoint().getX() + 1, (int)pa.getPoint().getY()))
			{
				temp = new Point();
				temp.setLocation((int)pa.getPoint().getX() + 1, pa.getPoint().getY());
				if(!isVisited(temp, pa.getLastPoint()))
				{
					Path createpath = new Path(pa.getPoint(), pa.getList());
					createpath.addPoint(pa.getPoint());
					createpath.setCurrent(temp);
					paths.add(createpath);
					if(paths.get(paths.size() -1).equals(end))
					{
						foundPac = true;
						break;
					}
				}
			}
			if(i == 2 && canMoveUp((int)pa.getPoint().getX(), (int)pa.getPoint().getY() - 1))
			{
				temp = new Point();
				temp.setLocation((int)pa.getPoint().getX(), pa.getPoint().getY() - 1);
				if(!isVisited(temp, pa.getLastPoint()))
				{
					Path createpath = new Path(pa.getPoint(), pa.getList());
					createpath.addPoint(pa.getPoint());
					createpath.setCurrent(temp);
					paths.add(createpath);
					if(paths.get(paths.size() -1).equals(end))
					{
						foundPac = true;
						break;
					}
				}
			}
			if(i == 3 && canMoveDown((int)pa.getPoint().getX(), (int)pa.getPoint().getY() + 1))
			{
				temp = new Point();
				temp.setLocation((int)pa.getPoint().getX(), pa.getPoint().getY() + 1);
				if(!isVisited(temp, pa.getLastPoint()))
				{
					Path createpath = new Path(pa.getPoint(), pa.getList());
					createpath.addPoint(pa.getPoint());
					createpath.setCurrent(temp);
					paths.add(createpath);
					if(paths.get(paths.size() -1).equals(end))
					{
						foundPac = true;
						break;
					}
				}
			}
			
		}
	}
	
	public boolean isVisited(Point last, Point now)
	{
		if(last.equals(now))
			return true;
		return false;
	}
	
	public void addPath(Path pa)
	{
		paths.add(pa);
	}
	
	public boolean canMoveLeft(int x, int y) {

		{
			if (x - 1 != -1) {
				if (wall(x - 1, y)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveRight(int x, int y) {
		{
			if (x + 1 != xSize) {
				if (wall(x + 1, y)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveUp(int x, int y) {
		{
			if (y - 1 != -1) {
				if (wall(x, y - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveDown(int x, int y) {
		{
			if (y + 1 != ySize + 1) {
				if (wall(x, y + 1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean wall(int x,int y)
	{
			if (g1[x][y]==1)
				return true;
			else
				return false;
	}
}
