package studio4harkka2;

import processing.core.PApplet;

public class Rectangle extends Particle {
	private int strokeWeight;
	private double directionAngle;
	
	
	public Rectangle(int newx, int newy, double maxVelocity, double xscale,
			double yscale, Placer parentPlacer, PApplet parentApplet, 
			int strokeWeight){
		
		super(newx, newy, maxVelocity, 0.0, 0.0, 0.0, xscale, yscale, -1,
				parentPlacer, parentApplet);
		this.strokeWeight = strokeWeight;
	}

	@Override
	public void drawSelf() {
		
		//System.out.println(getX() + ", " + getY());
		
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
		
	}

	@Override
	public void onMousePressed() {
		
		
	}

	@Override
	public void onMouseDown() {
		
	}
	
}
