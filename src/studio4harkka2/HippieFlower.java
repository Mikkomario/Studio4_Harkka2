package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class HippieFlower extends Particle {
	private PImage image;
	
	private static Random rand = new Random();
	
	public HippieFlower(int newx, int newy, double maxVelocity, double friction, 
			double maxRotation, double rotationFriction, double xscale,
			double yscale, Placer parentPlacer, PApplet parentApplet, 
			PImage image){
		super(newx, newy, maxVelocity, friction, 
				maxRotation, rotationFriction, xscale,
				yscale, 250, parentPlacer, parentApplet);
		this.image = image;
	}

	@Override
	public void drawSelf() {
		// TODO Auto-generated method stub
		this.getApplet().image(this.image, this.getX(), this.getY());
	}

	@Override
	public boolean positionIsOver(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMouseOver() {
		//this.method will make the flower move
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed() {
		//this method will create new flowerparticles
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDown() {
		//this method is not needed
	}
	
}
