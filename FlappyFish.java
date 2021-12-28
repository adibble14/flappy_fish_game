import java.util.*;
import javax.swing.JOptionPane;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;
import java.awt.Color;
import java.awt.event.KeyEvent;


/* Andrew Dibble, Devin Hanson, Yahya Bakhtiar
 * C SCI 143
 * Final Game Group Project
 * Date: 6/6/2020
 * Purpose: To create a game similar to the classic arcade game Flappy Bird, while also adding
 * our own twist to it with Super Mario Bros and an under water adventure!
 */
public class FlappyFish extends GraphicsProgram {
	
	LinkedList<Obstacle> botTubes = new LinkedList<>(); //linked list of tubes
	LinkedList<TopObstacle> topTubes = new LinkedList<>();
	
	public static String direction = "false";
	int score = 0;
	public static int num;
	public static level[] a = new level[5];
	
	private static final SoundClip marioMusic = new SoundClip("C:\\Users\\andre\\Downloads\\mario au.au");
	private static final GImage coralImage1 = new GImage("C:\\Users\\andre\\Downloads\\coralBackGround.png");
	
	public static GRect ceiling;
	public static GRect ground;
	public static Fish fish;
	public static GLabel gameScore;
	
	private final int SIZE = 500;
	private final int SQUARE_SIZE = 175;
	
	public void init() {

		setBackground(Color.BLUE);
		setSize(SIZE, SIZE);
		addKeyListeners( );
				
	}
	public void run() {
		
		String start = JOptionPane.showInputDialog("Welcome to Flappy Fish! \n The rules are simple: \n use the space bar to jump"
				+ " \n and don't hit the tubes! \n type 'yes' to start" );
		try{
			
			if(start.charAt(0) == 'y')
			game();
			
		}
		
		catch(Exception e) {
			
		}
	}
	
	public void game() {
		
		GImage coral = new GImage(""); //adding decoration
		coral.setImage(coralImage1.getImage());
		coral.setSize(1400, 800);
		coral.setLocation(0, 0);
		add(coral);
		
		GRect square = new GRect(365,275,SQUARE_SIZE,SQUARE_SIZE); //editing the image to cover up some fish
		square.setColor(Color.BLUE);
		square.setFilled(true);
		add(square);
		
		GRect square1 = new GRect(460,30,SQUARE_SIZE,SQUARE_SIZE);
		square1.setColor(Color.BLUE);
		square1.setFilled(true);
		add(square1);
		
		GRect square2 = new GRect(970,50,SQUARE_SIZE+25,SQUARE_SIZE);
		square2.setColor(Color.BLUE);
		square2.setFilled(true);
		add(square2);
		
		
		ceiling = new GRect(0,-500,1400,100); //adds a ceiling
		ceiling.setColor(Color.BLUE);
		ceiling.setFilled(true);
		add(ceiling);
		
		Color brown = new Color(51,25,0);
		ground = new GRect(0,650,1400,100); //adding the ground
		ground.setColor(brown);
		ground.setFilled(true);
		add(ground);
		
		gameScore = new GLabel("SCORE: "+score); // adding score
		gameScore.setFont("*-BOLD-40"); 
		add(gameScore,575,100);
		
		num = 300+(int)(Math.random()*301); //random number to provide random tube placement
		botTubes.add(new Obstacle(num));  //adding a new obstacle at start of game
		topTubes.add(new TopObstacle(num-850));
		add(botTubes.getFirst());
		add(topTubes.getFirst());
		
		fish = new Fish(); //adding the fish
		add(fish);
		
		marioMusic.setVolume(.5); //add music
		marioMusic.loop();
		
		a[0] = new level(6,3.25); //values of the levels in an array for organization
		a[1] = new level(5.25,2.5);
		a[2] = new level(5,2.25);
		a[3] = new level(4.5,1.75);
		a[4] = new level(4,1.25);
		
		
		while(true){
			
			botTubes.getFirst().move(-1, 0); // start Obstacle code
			topTubes.getFirst().move(-1, 0);
			
			   if(score<=4){    //levels of hardness: level 1
				   if(botTubes.size()==1) 
						pause(a[0].one);
					else
						pause(a[0].two);
				}
			   
				else if(score<=9){    //level 2
					if(botTubes.size()==1) 
						pause(a[1].one);
					else
						pause(a[1].two);
				}
			   
				else if(score <= 14){  //level 3
					if(botTubes.size()==1) 
						pause(a[2].one);
					else
						pause(a[2].two);
				}
			   
				else{                //level 4
					if(botTubes.size()==1) 
						pause(a[3].one);
					else
						pause(a[3].two);
				}
			 
			
			if(botTubes.getFirst().getX()<-700 && botTubes.size()<2) { //making a new obstacle after a certain point
				num = 300+(int)(Math.random()*301);
				botTubes.add(new Obstacle(num));
				topTubes.add(new TopObstacle(num-850));
				add(botTubes.getLast());
				add(topTubes.getLast());
			}
			
			botTubes.getLast().move(-1, 0); //animating the new obstacles
			topTubes.getLast().move(-1, 0);

			if(botTubes.getFirst().getX()<-1400) { //only allowing two obstacles in list
				botTubes.remove(0); 
				topTubes.remove(0);                 //end Obstacle code
			}
			
			
			if(direction.equals("up")) {    //fish movement
				fish.setLocation(fish.getLocation().getX(), fish.getLocation().getY() - 2);
			}

			if(direction.equals("down")) {
				fish.setLocation(fish.getLocation().getX(), fish.getLocation().getY() + 1);
			}
			
   		
			GRectangle obstacleBoundary1 = botTubes.getFirst().getBounds(); //creating boundaries for the objects
			GRectangle obstacleBoundary2 = topTubes.getFirst().getBounds();
			GRectangle fishBoundary = fish.getBounds(); 
			GRectangle groundBoundary = ground.getBounds();
			GRectangle ceilingBoundary = ceiling.getBounds();
			
			if (fishBoundary.intersects(obstacleBoundary1) || fishBoundary.intersects(obstacleBoundary2)||
					fishBoundary.intersects(groundBoundary) || fishBoundary.intersects(ceilingBoundary)) { //detecting collision
				
				marioMusic.stop();
				
				GLabel gameOver = new GLabel("GAME OVER"); //game over label
				gameOver.setFont("*-BOLD-70"); 
				add(gameOver,450,200);
				
				remove(gameScore);
				
				GLabel finalScore = new GLabel("Final Score: "+score);
				finalScore.setFont("*-BOLD-40"); 
				add(finalScore,550,100);
				
				String playAgain = JOptionPane.showInputDialog("PLAY AGAIN? \n Type 'y' to play or hit cancel" );  //play again?
				
				try {
					
					if(playAgain.charAt(0)=='y') {
						remove(fish);
						remove(botTubes.getFirst());
						remove(botTubes.getLast());
						remove(topTubes.getFirst());
						remove(topTubes.getLast());
						remove(finalScore);
						remove(gameOver);
						remove(ground);
						remove(ceiling);
						score=0;
						botTubes.removeAll(botTubes);
						topTubes.removeAll(topTubes);
						pause(100);
						game();
						
					}
				}
				catch(Exception e) {
					
					break;
					
				}
			}
			
			else {
				
				if(obstacleBoundary1.getX() == 25) {
					score++;
					gameScore.setLabel("SCORE: "+score);
					
				}
				
			}
		}
		
	}
	

	
	public void keyPressed(KeyEvent e) { //key controls
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				direction = "up";  break;

		}
	}

	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				direction = "down"; break;
		}
	}

}
class level{
	
	public double one;double two;
	
	public level() {
		
	}
	
	public level(double a, double b) {
		this.one = a;
		this.two = b;
	}
}