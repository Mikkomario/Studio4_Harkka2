package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

public class Rectangle extends Particle {
	private boolean isMoving;
	private double directionAngle;
	private double phaseAngle;
	private int strokeWeight;
	private static Random rand = new Random();
	
	
	public Rectangle(int newx, int newy, double maxVelocity, double xscale,
			double yscale, Placer parentPlacer, PApplet parentApplet, 
			int strokeWeight){
		
		super(newx, newy, maxVelocity, 0.0, 0.0, 0.0, xscale, yscale, 10,
				parentPlacer, parentApplet);
		this.strokeWeight = strokeWeight;
		this.directionAngle = 2*Math.PI*rand.nextDouble();
		this.phaseAngle = 0;
		this.isMoving = false;
	}

	@Override
	public void drawSelf() {
		
		
		this.getApplet().stroke(0);
		
		this.getApplet().strokeWeight(this.strokeWeight);
		
		this.getApplet().noFill();
		
		this.getApplet().rect(-25, -25, 50, 50, 10, 10);
		
	}

	@Override
	public boolean positionIsOver(int x, int y) {
		double halfWidth = this.getXScale()*50/2;
		double halfHeight = this.getYScale()*50/2;
		if(x > this.getX()-halfWidth && x < this.getX()+halfWidth &&
			y > this.getY()-halfHeight && y < this.getY()+halfHeight){
			return true;
		}
		return false;
	}

	@Override
	public void onMouseOver() {
		//this method isn't needed
	}

	@Override
	public void onMousePressed() {
		if(this.isMoving){
			this.isMoving = false;
		}
		else {
			this.isMoving = true;
		}
	}

	@Override
	public void onMouseDown() {
		//this method isn't needed
	}
	
	public boolean isMoving(){
		if(this.isMoving){
			return true;
		}
		return false;
	}
	
	public void moveParticle(){
		if(this.isMoving){
					
			int x = (int)(Math.sin(directionAngle)*Math.sin(phaseAngle)*100);
			int y = (int)(Math.cos(directionAngle)*Math.cos(phaseAngle)*100);
			
			this.setPosition(x, y);
			
			if(this.phaseAngle < 2*Math.PI) {
				this.phaseAngle = this.phaseAngle+0.1;
			}
			else {
				this.phaseAngle = this.phaseAngle-0.1;
			}
		}
	}
}
