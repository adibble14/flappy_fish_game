 import acm.graphics.*;  

public class Obstacle extends GCompound {
	
	private final int WIDTH = 100;
	private final int HEIGHT = 600;
	
	private static final GImage marioTubeImage1 = new GImage("C:\\Users\\andre\\Downloads\\MarioTube.jpg");
	

	
	public Obstacle(int n) {
		
		int y = n;
		
		GImage tube1 = new GImage("");
		tube1.setImage(marioTubeImage1.getImage());
		tube1.setSize(WIDTH, HEIGHT);
		tube1.setLocation(1300, y);
		add(tube1);
		

	}
	
}
