import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class lives{
	private Image forward; //backward, left, right; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.8;		//change to scale image
	double scaleHeight = 0.8; 		//change to scale image

	public lives() {
		
		//load the main image (front or forward view)
		forward 	= getImage("/imgs/"+"life cat.png");

		//width and height for hitbox
		width = 64;
		height = 42;
		
		//used for placement on the JFrame
		x = 600/2-width/2;
		y = 910;
		
		//if your movement will not be "hopping" base
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	
	//2nd constructor - allow setting x and y during construction
	public lives(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this(); //invokes default constructor
		
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
		
	}
	
	/*
	 * movement
	 */
	public void move(int dir) {
		
		switch(dir) {
		
		case 0: //hop up
			y-=height; //move up a body length
			
			break;
		
		case 1: //hop down
			y+=height;
			break;
		
		case 2: //hop left
			x-=width;
			break;
		
		case 3: //hop right
			x+=width;
			break;
			
		}
		
	}
	
	
	/*
	 * getters
	 */
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update x and y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		g2.drawImage(forward, tx, null);
		
		//draw hit box based on x, y, width, height
		//for collision detection
		if(Frame.debugging) {
			//draw hit box only if debugging
			g.setColor(Color.green);
			g.drawRect(x, y+10, width, height-10);
		}
		
		}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = lives.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
