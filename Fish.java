import acm.graphics.*;  

public class Fish extends GCompound {
	
	private static final GImage marioFish = new GImage("C:\\Users\\andre\\Downloads\\MarioFish.png");
	
	
	public Fish() {
		
		GImage fish = new GImage("");
		fish.setImage(marioFish.getImage());
		fish.setSize(100, 100);
		fish.setLocation(100, 400);
		add(fish);
		
	}
	
}
