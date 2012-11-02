package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
 * Rectangle repesents a single visual rectangle drawn on the screen.
 * The class extends Particle. Knows its position, has a velocity 
 * and can draw itself onto the screen. Starts moving when mouse pressed on its
 * position.
 * @author Tiitu
 *
 */
public class Rectangle extends Particle {
	private boolean isMoving;
	private double directionAngle;
	private double phaseAngle;
	private double originalVelocity;
	private int x;
	private int y;
	private int distance;
	private int strokeWeight;
	private int slowDown;
	private static Random rand = new Random();
	
	/**
	 * Constructor
	 * 
	 * @param newx new position's x coordinate
	 * @param newy new position's y coordinate
	 * @param maxVelocity defines the beginning velocity of the rectangle
	 * @param xscale defines how much the width is scaled
	 * @param yscale defines how much the height is scaled
	 * @param parentPlacer defines the parent placer of the rectangle
	 * @param parentApplet defines the parent applet of the rectangle
	 * @param strokeWeight defines how thick the border of the rectangle is
	 */
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
		this.x = (int)(Math.sin(this.directionAngle)*Math.sin(this.phaseAngle)
				*this.distance);
		this.y = (int)(Math.cos(this.directionAngle)*Math.sin(this.phaseAngle)
				*this.distance);
		this.slowDown = 0;
		this.originalVelocity = this.getMaxVelocity();
	}

	/**
	 * Method that draws the rectangle.
	 */
	@Override
	public void drawSelf() {
		
		this.getApplet().stroke(0);
		
		this.getApplet().strokeWeight(this.strokeWeight);
		
		this.getApplet().noFill();
		
		this.getApplet().rect(-25+this.x, -25+this.y, 50, 50, 
								10, 10);
		
	}

	/**
	 * Method that tells whether the mouse is over the rectangle.
	 * @return true, if the mouse is over the rectangle, otherwise false
	 */
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

	/**
	 * Starts the moving of the particle
	 */
	@Override
	public void onMousePressed() {
		this.isMoving = true;
	}

	@Override
	public void onMouseDown() {
		//this method isn't needed
	}
	
	/**
	 * Checks whether the rectangle is on the move.
	 * @return true, if the rectangle is moving, false, if the rectangle is 
	 * still
	 */
	public boolean isMoving(){
		if(this.isMoving){
			return true;
		}
		return false;
	}
	
	/**
	 * Method, that moves rectangle, if the rectangle is in moving mode and 
	 * slows the movement down if it has been moving for a certain time. When 
	 * the velocity reaches zero, the method stops sets the original velocity
	 * as the velocity and sets the moving status to false
	 */
	public void moveParticle(){
		if(this.isMoving){
					
			this.x = (int)(Math.sin(this.directionAngle)
					*Math.sin(this.phaseAngle)*this.distance);
			this.y = (int)(Math.cos(this.directionAngle)
					*Math.sin(this.phaseAngle)*this.distance);
			
			this.phaseAngle = this.phaseAngle+0.1;
			
			this.slowDown++;
			
			if(this.slowDown > 200){
				this.setMaxVelocity(this.getMaxVelocity()-0.05);
				if(this.getMaxVelocity() == 0){
					this.isMoving = false;
					this.setMaxVelocity(this.originalVelocity);
					this.slowDown= 0;
					this.phaseAngle = 0;
					this.setPosition(this.getX()+this.x, this.getY()+this.y);
				}
			}
		}	
	}
	
}
