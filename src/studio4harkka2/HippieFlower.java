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
		System.out.println("luodaan kukkia"+ this.getDuration());
	}

	@Override
	public void drawSelf() {
		System.out.println("yrittää piirtää");
		this.getApplet().image(this.image, this.image.width/2, 
								this.image.height/2);
	}

	@Override
	public boolean positionIsOver(int x, int y) {
		int bigger;
		if(this.image.width > this.image.height){
			bigger = this.image.width;
		}
		else {
			bigger = this.image.height;
		}
		int maxDist = (int) (bigger*(Math.max(getXScale(), getYScale())));
		
		return (Studio4Harkka2.pointDistance(getX(), getY(),
				x, y) < maxDist);
	}

	@Override
	public void onMouseOver() {
		if (getRotation() == 0)
			setRotation(20*(rand.nextDouble() - 0.5));
		else
			addRotation(getRotation()*0.05);
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
