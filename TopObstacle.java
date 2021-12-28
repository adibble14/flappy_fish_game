 import acm.graphics.*;  

public class TopObstacle extends GCompound {
	
	private final int WIDTH = 100;
	private final int HEIGHT = 600;
	
	private static final GImage marioTubeImage2 = new GImage("C:\\Users\\andre\\Downloads\\MarioTube2.png");

	
	public TopObstacle(int n) {
		
		int y = n;
		
		GImage tube2 = new GImage("");
		tube2.setImage(marioTubeImage2.getImage());
		tube2.setSize(WIDTH, HEIGHT);
		tube2.setLocation(1300, y);
		add(tube2);

	}
	
}
