package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

public class Rectangle extends Particle {
	private boolean isMoving;
	private double directionAngle;
	private double phaseAngle;
	private int x;
	private int y;
	private int distance;
	private int strokeWeight;
	private static Random rand = new Random();
	
	
	public Rectangle(int newx, int newy, double maxVelocity, double xscale,
			double yscale, Placer parentPlacer, PApplet parentApplet, 
			int strokeWeight){
		
		super(newx, newy, maxVelocity, 0.0, 0.0, 0.0, xscale, yscale, -1,
				parentPlacer, parentApplet);
		this.strokeWeight = strokeWeight;
		this.directionAngle = 2*Math.PI*rand.nextDouble();
		this.phaseAngle = 0;
		this.isMoving = false;
		this.distance = 20+rand.nextInt(140);
		this.x = (int)(Math.sin(directionAngle)*Math.sin(phaseAngle)*this.distance);
		this.y = (int)(Math.cos(directionAngle)*Math.cos(phaseAngle)*this.distance);
	}

	@Override
	public void drawSelf() {
		
		this.getApplet().stroke(0);
		
		this.getApplet().strokeWeight(this.strokeWeight);
		
		this.getApplet().noFill();
		
		this.getApplet().rect(-25+this.x, -25+this.y, 50, 50, 
								10, 10);
		
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
					
			this.x = (int)(Math.sin(directionAngle)*Math.sin(phaseAngle)*this.distance);
			this.y = (int)(Math.cos(directionAngle)*Math.cos(phaseAngle)*this.distance);
			
			this.phaseAngle = this.phaseAngle+0.1;
		}
	}
}
