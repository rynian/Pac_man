import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Game extends GameEngine {

	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	int xSize;
	int ySize;
	int dir = -1;
	int x;
	int y;
	int ghost[][] = new int[2][4];
	int pellet[] = new int[4];
	int timebox[] = new int[4];
	int speed = 1;
	boolean canMoveLeft = false;
	boolean canMoveRight = false;
	boolean canMoveUp = false;
	boolean canMoveDown = false;
	pac p;
	ghost pinky, inky, blinky, sue;
	int timer;
	boolean Super = false;

	Maze m1;

	int[][] maze3 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 4, 4, 4, 4, 4, 4, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 4, 4, 4, 4, 4, 4, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
			{ 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
			{ 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static void main(String[] args) {
		Game g = new Game();
		g.windowWidth = 980;
		g.windowHeight = 1008;
		g.init();
		g.run();
		System.exit(0);
	}

	void update() {

//		if (!Super) {
//			if (isGhost(p.getX(), p.getY()))
//				isRunning = false;
//		}
		if (m1.isPel(x, y)) {
			timer = 0;
			Super = true;
			supPel();
			p.setX(x);
			m1.setLevel(x, y, 2);
		} 
		if (timer > 20) {
			supPelEnd();
			timer = 0;
			Super = false;
		}
		timer++;

		if (input.isKeyDown(KeyEvent.VK_RIGHT) && canMoveRight(x, y)) {
			m1.setLevel(x, y, 4);
			x += 1;

			if (x == xSize) {
				y = 0;
				p.setX(x);
			}

			p.setX(x);
//			m1.setLevel(x, y, 2);
		} else if (input.isKeyDown(KeyEvent.VK_LEFT) && canMoveLeft(x, y)) {

			m1.setLevel(x, y, 4);
			x -= 1;

			if (x == -1) {
				x = xSize;
				p.setX(x);
			}
//			
			p.setX(x);
//			m1.setLevel(x, y, 2);
		} else if (input.isKeyDown(KeyEvent.VK_DOWN) && canMoveDown(x, y)) {
			m1.setLevel(x, y, 4);
			y += 1;

			if (y == ySize + 1) {
				y = 0;
				p.setY(y);
			}
//			
			p.setY(y);
//			m1.setLevel(x, y, 2);
		} else if (input.isKeyDown(KeyEvent.VK_UP) && canMoveUp(x, y)) {
			m1.setLevel(x, y, 4);
			y -= 1;

			if (y == -1) {
				y = ySize;
				p.setY(y);
			}
//			
			p.setY(y);
//			m1.setLevel(x, y, 2);
		}
//		

		// inky
		if(!Super)
		{
			inky.setPath(DoBFS.BFS(new Point(inky.getX(), inky.getY()), new Point(p.getX(), p.getY()), maze3));
		}
		if(Super)
		{
			inky.setPath(DoBFS.BFS(new Point(inky.getX(), inky.getY()), new Point(xSize - p.getX(), ySize - p.getY()), maze3));
		}
		inky.update(Super);
//		if (!Super) {
//			if (p.getX() == inky.getX() && p.getY() == inky.getY()) {
//				System.out.println("im in here");
//				isRunning = false;
//			}
//		}

		// pinky
		if(!Super)
		{
			pinky.setPath(DoBFS.BFS(new Point(pinky.getX(), pinky.getY()), new Point(p.getX(), p.getY()), maze3));
		}
		if(Super)
		{
			pinky.setPath(DoBFS.BFS(new Point(pinky.getX(), pinky.getY()), new Point(xSize - p.getX(), ySize - p.getY()), maze3));
		}
		pinky.update(Super);
//		if (!Super) {
//			if (p.getX() == pinky.getX() && p.getY() == pinky.getY()) {
//				System.out.println("im in here");
//				isRunning = false;
//			}
//		}

		// blinky
		if(!Super)
		{
			blinky.setPath(DoBFS.BFS(new Point(blinky.getX(), blinky.getY()), new Point(p.getX(), p.getY()), maze3));
		}
		if(Super)
		{
			blinky.setPath(DoBFS.BFS(new Point(blinky.getX(), blinky.getY()), new Point(xSize - p.getX(), ySize - p.getY()), maze3));
		}
		blinky.update(Super);
//		if (!Super) {
//			if (p.getX() == blinky.getX() && p.getY() == blinky.getY()) {
//				System.out.println("im in here");
//				isRunning = false;
//			}
//		}

		// sue
		if(!Super)
		{
			sue.setPath(DoBFS.BFS(new Point(sue.getX(), sue.getY()), new Point(p.getX(), p.getY()), maze3));
		}
		if(Super)
		{
			sue.setPath(DoBFS.BFS(new Point(sue.getX(), sue.getY()), new Point(xSize - p.getX(), ySize - p.getY()), maze3));
		}
		sue.update(Super);
//		
//		
		
		ArrayList<ghost> ghosts = new ArrayList<ghost>();
		ghosts.add(inky);
		ghosts.add(pinky);
		ghosts.add(blinky);
		ghosts.add(sue);
		
		for (ghost g : ghosts){
			if (g.getX() == p.getX() && g.getY() == p.getY())
				if (Super)
					g.defualt();
				else isRunning = false;
		}
		
		
			
	}

	public boolean isGhost(int x, int y) {
		if (blinky.getX() == x && blinky.getY() == y)
			return true;
		if (inky.getX() == x && blinky.getY() == y)
			return true;
		if (pinky.getX() == x && blinky.getY() == y)
			return true;
		if (sue.getX() == x && blinky.getY() == y)
			return true;
		else
			return false;
	}

	public void supPel() {
		inky.setColor(Color.BLUE);
		pinky.setColor(Color.BLUE);
		blinky.setColor(Color.BLUE);
		sue.setColor(Color.BLUE);
	}

	public void supPelEnd() {
		inky.setColor(Color.CYAN);
		pinky.setColor(Color.PINK);
		blinky.setColor(Color.RED);
		sue.setColor(Color.ORANGE);
	}

	public boolean canMoveLeft(int x, int y) {

		{
			if (x - 1 != -1) {
				if (m1.wall(x - 1, y)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveRight(int x, int y) {
		{
			if (x + 1 != xSize) {
				if (m1.wall(x + 1, y)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveUp(int x, int y) {
		{
			if (y - 1 != -1) {
				if (m1.wall(x, y - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMoveDown(int x, int y) {
		{
			if (y + 1 != ySize + 1) {
				if (m1.wall(x, y + 1)) {
					return false;
				}
			}
		}
		return true;
	}

	void init() {
		m1 = new Maze(maze3, windowWidth, windowHeight);

		ySize = (windowWidth / m1.getLevel().length);
		xSize = (windowHeight / m1.getLevel()[0].length);
		x = 4;
		y = 1;
		p = new pac(x, y, ySize, xSize);
		inky = new ghost(Color.CYAN, 20, 15, ySize, xSize, 0);
		pinky = new ghost(Color.PINK, 17, 13, ySize, xSize, 10);
		blinky = new ghost(Color.RED, 18, 13, ySize, xSize, 20);
		sue = new ghost(Color.ORANGE, 16, 12, ySize, xSize, 30);
		
		try {
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(new java.net.URL("PacMusic.mp3"));
			clip.play();
			} 
		catch (java.net.MalformedURLException murle) {
			System.out.println(murle);
			}
	}

	void draw(Graphics g) {
		g = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, windowWidth, windowHeight);

		m1.draw(g);
		p.draw(g);
		pinky.draw(g);
		inky.draw(g);
		blinky.draw(g);
		sue.draw(g);

	}
}
