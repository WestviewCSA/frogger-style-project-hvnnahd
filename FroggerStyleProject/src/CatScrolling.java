import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class CatScrolling{
	private Image forward; //backward, left, right; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.7;		//change to scale image
	double scaleHeight = 0.7; 		//change to scale image

	public CatScrolling() {
		
		//load the main image (front or forward view)
		forward 	= getImage("/imgs/"+"backgroundcatsmall.png");

		//width and height for hitbox
		width = 100;
		height = 70;
		
		//used for placement on the JFrame
		x = -width; //off screen for now
		y = 600;
		
		//if your movement will not be "hopping" base
		vx = -4;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	/*
	 * collision detection with main character class
	 */
	public boolean collided(Cat character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight()
				);
		
		Rectangle thisObject = new Rectangle(x, y, width, height);
		
		
		//user built in method to check intersection (collision)
		return main.intersects(thisObject);
		
	}
	
	
	//2nd constructor - allow setting x and y during construction
	public CatScrolling(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this(); //invokes default constructor
		
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update x and y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		//for infinite scrolling - teleport to the other side
		//once it leaes the other side
		if(x < -200) {
			x = 800;
		}
		
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
			URL imageURL = CatScrolling.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
