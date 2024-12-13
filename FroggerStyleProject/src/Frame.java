import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//for any debugging code we add
	public static boolean debugging = true;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
	
	Cat kitty 		= new Cat();
	carpet omg = new carpet();
	bland er = new bland();
	rug herm = new rug();
	paws shoot = new paws();
	
	//a row of CatScrolling objects!
	CatScrolling[] row1 = new CatScrolling[4];
	
	FastCat[] row2 = new FastCat[2];
	
	SlowCat[] row3 = new SlowCat[3];
	ArrayList<SlowCat> row3list = new ArrayList<SlowCat>();
	
	RumbaSlow[] row4 = new RumbaSlow[5];
	
	RumbaFast[] row5 = new RumbaFast[5];
	
	ArrayList<lives> lives = new ArrayList<lives>();
	
	//frame width/height
	int width = 600;
	int height = 1050;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//paint the other objects on the screen
		omg.paint(g);
		er.paint(g);
		kitty.paint(g);
		herm.paint(g);
		shoot.paint(g);
		
		
		//have the row1 objects paint on the screen!
		//for each obj in row1
		for(CatScrolling obj : row1) {
			obj.paint(g);
		}
		
		for(FastCat obj : row2) {
			obj.paint(g);
		}
		
		for(SlowCat obj : row3) {
			obj.paint(g);
		}
		
		for (SlowCat obj : row3list) { //for every SlowCat obj in row3list
			obj.paint(g);
		}
		
		for (lives obj : lives) {
			obj.paint(g);
		}
		
		for(RumbaSlow obj : row4) {
			obj.paint(g);
		}
		
		for(RumbaFast obj : row5) {
			obj.paint(g);
		}
		
		kitty.paint(g);
		
int lleft = lives.size()-1;	
	//collision detection
	//for each catscrolling object in row1 array
	for(CatScrolling obj : row1 ) {
		//invoke the collided method for your class - pass the main character as your argument
		if (obj.collided(kitty)) {
			System.out.println("the kitty has been executed lion king style. are you proud of yourself?");
			kitty.setX(280);
			kitty.setY(910);
			lives.remove(lleft);
			lleft--;
			SimpleAudioPlayer winning = new SimpleAudioPlayer("110010__tuberatanka__cat-meow-ii.wav", true);
		}
	}
	

	for(FastCat obj : row2 ) {
		//invoke the collided method for your class - pass the main character as your argument
		if (obj.collided(kitty)) {
			System.out.println("the kitty has been executed lion king style. are you proud of yourself?");
			kitty.setX(280);
			kitty.setY(910);
			lives.remove(lleft);
			lleft--;
			SimpleAudioPlayer winning = new SimpleAudioPlayer("110010__tuberatanka__cat-meow-ii.wav", true);
			
		}
	}
	
	

	for(SlowCat obj : row3 ) {
		//invoke the collided method for your class - pass the main character as your argument
		if (obj.collided(kitty)) {
			System.out.println("the kitty has been executed lion king style. are you proud of yourself?");
			kitty.setX(280);
			kitty.setY(910);
			lives.remove(lleft);
			lleft--;
			SimpleAudioPlayer winning = new SimpleAudioPlayer("110010__tuberatanka__cat-meow-ii.wav", true);
			
			
		}
	}
	
	boolean safe;
	if(!herm.collided(kitty)) {
		safe = true;
		kitty.setVX(0);
	}else {
		safe = false;
	}
	
	for(RumbaSlow obj : row4) {
		if(obj.collided(kitty) && herm.collided(kitty)) {
			safe = true;
			kitty.setVX(obj.getVX()+2);
		}
	}
	
	for(RumbaFast obj : row5) {
		if(obj.collided(kitty) && herm.collided(kitty)) {
			safe = true;
			kitty.setVX(obj.getVX()-3);
		}
	}
	
	if(!safe) {
		System.out.println("the kitty has been tossed off a rumba and then run over, thereby creating a paste. you monster.");
		kitty.setX(280);
		kitty.setY(910);
		lives.remove(lleft);
		lleft--;
		SimpleAudioPlayer winning = new SimpleAudioPlayer("421877__sventhors__ouch_1.wav", true);
	}
	
	while (kitty.getX() < 0) {
		kitty.setX(0);
	}
	while (kitty.getY() > 910) {
		kitty.setY(910);
	}
	while (kitty.getX() > 520) {
		kitty.setX(520);
	}
	while (kitty.getY() < 0) {
		kitty.setY(0);
	}
	
	
	
	
	if (kitty.getX() > 250 && kitty.getX() < 310 && kitty.getY() < 11) {
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("You Won!", 220, 140);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Press Enter to Restart", 200, 170);
		for (int i = lleft; i < 3; i++) {
			this.lives.add(new lives(i*70, 10));
		}
		lleft = lives.size()-1;

		SimpleAudioPlayer winning = new SimpleAudioPlayer("60443__jobro__tada1.wav", true);
		
	}


	if (lleft < 0) {
		g.setColor(Color.pink);
		g.fillRect(0, 0, 600, 1200);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("The kitty starved.", 20, 500);
		g.drawString("Are you happy with yourself?", 20, 550);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Press Enter to try again", 20, 750);
		g.drawString("or not. the damage has been done already.", 20, 800);
		}

	
	
	
	

	}
	
	
	
	
	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
//		backgroundMusic.play();
		
		/*
		 * setup any 1d array here! - create the objects that go in them 
		 */
		//traverse the array
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new CatScrolling(i*250,820);
		}

		for(int i = 0; i < row2.length; i++) {
			row2[i] = new FastCat(i*500,720);
		}
		
		for (int i = 0; i < row3.length; i++) {
			row3[i] = new SlowCat(i*350, 620);
		}
		
		for (int i = 0; i < 3; i++) {
			this.row3list.add(new SlowCat(i*350, 620));
		}
		
		for (int i = 0; i < row4.length; i++) {
			row4[i] = new RumbaSlow(i*200, 310);
		}
		
		for (int i = 0; i < row5.length; i++) {
			row5[i] = new RumbaFast(i*200, 215);
		}
		
		for (int i = 0; i < 3; i++) {
			this.lives.add(new lives(i*70, 10));
		}

	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==87) {
			//move main character up
			kitty.move(0);
		}else if(arg0.getKeyCode()==83) {
			//move main character down
			kitty.move(1);
		}else if(arg0.getKeyCode()==65) {
			kitty.move(2);
		}else if(arg0.getKeyCode()==68)
		{
			kitty.move(3);
		}else if(arg0.getKeyCode()==10 && kitty.getX() > 250 && kitty.getX() < 310 && kitty.getY() < 30) {
			kitty.setX(280);
			kitty.setY(910);
		}else if(arg0.getKeyCode()==10) {
			for (int i = lives.size(); i < 3; i++) {
				this.lives.add(new lives(i*70, 10));
			}
			kitty.setX(280);
			kitty.setY(910);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
