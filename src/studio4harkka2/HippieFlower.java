package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**HippieFlower repesents a single visual image drawn on the screen.
* The class extends Particle. It may be a flower of some kind or a peace sign.
* Knows its position, has a velocity and can draw itself onto the screen. 
* When mouse is over it, it starts rotating.
* @author - Tiitu
*/
public class HippieFlower extends Particle {
	private PImage image;
	private static Random rand = new Random();
	
	/**
	 * Constructor
	 * 
	 * @param newx new position's x coordinate
	 * @param newy new position's y coordinate
	 * @param maxVelocity defines the beginning velocity of the hippie-flower
	 * @param friction defines how much the hippie-flower slows down at each 
	 * step
	 * @param maxRotation defines the maximum rotation of the hippie-flower
	 * @param scale defines how much the width and height are scaled
	 * @param parentPlacer defines the parent placer of the hippie-flower
	 * @param parentApplet defines the parent applet of the hippie-flower
	 * @param image defines the image used with the hippie-flower
	 */
	public HippieFlower(int newx, int newy, double maxVelocity, double friction, 
			double maxRotation, double scale, Placer parentPlacer,
			PApplet parentApplet, PImage image){
		super(newx, newy, maxVelocity, friction, 
				maxRotation, 0, scale, scale, 750, parentPlacer, parentApplet);
		this.image = image;
	}

	/**
	 * Draws the hippie-flower using image-method
	 */
	@Override
	public void drawSelf() {
		this.getApplet().image(this.image, -this.image.width/2, 
								-this.image.height/2);
	}

	/**
	 * Checks whether the mouse is over the hippie-flower
	 */
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

	/**
	 * Method that sets a value for rotation for the hippie-flower when the 
	 * mouse is over it or speeds it if it already is rotating
	 */
	@Override
	public void onMouseOver() {
		if (getRotation() == 0)
			setRotation(26*(rand.nextDouble() - 0.5));
		else
			addRotation(getRotation()*0.05);
	}

	@Override
	public void onMousePressed() {
		//this method is not needed
	}

	@Override
	public void onMouseDown() {
		//this method is not needed
	}
	
}
