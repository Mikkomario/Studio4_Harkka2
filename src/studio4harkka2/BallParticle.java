package studio4harkka2;

import processing.core.PApplet;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Gandalf.
 *         Created 30.10.2012.
 */
public class BallParticle extends Particle
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private int lifeTime;
	private int startLifeTime;
	private double gravityDirection;
	private double gravityForce;
	

	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new ball that has a random gravity and given attributes
	 * The ball's brightness is determined by its position
	 *
	 * @param newx the ball's starting position's x coordinate
	 * @param newy the ball's starting position's y coordinate
	 * @param scale the size of the ball
	 * @param gravityDirection the ball's gravity's direction in radians (!)
	 * @param gravityForce How large a gravitation will affect the ball
	 * (velocity / step)
	 * @param duration How long the ball will stay in the screen
	 * @param parentPlacer the placer who created the ball
	 * @param parentApplet the applet to which the ball is drawn
	 */
	public BallParticle(int newx, int newy, double scale,
			double gravityDirection, double gravityForce, int duration,
			Placer parentPlacer, PApplet parentApplet)
	{
		super(newx, newy, 20, 0, 0, 0, scale,
				scale, -1, parentPlacer, parentApplet);
		// Initializes attributes
		this.lifeTime = duration;
		this.startLifeTime = this.lifeTime;
		this.gravityDirection = gravityDirection;
		this.gravityForce = gravityForce;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void drawSelf()
	{
		// Determines the brightness by the ball's position
		int brightness = 30 + (1 - getY() / getApplet().height) * 70;
		
		// Draws a yellow / red ball
		getApplet().stroke(0, 100, brightness);
		getApplet().strokeWeight(10);
		getApplet().fill(10, 100, brightness);
		
		getApplet().ellipse(-25, -25, 50, 50);
		
	}

	@Override
	public boolean positionIsOver(int x, int y)
	{
		// The position is over if it's withing a certain range from the origin
		// The range is a bit larger than the ball because it wants to react
		// further
		return Studio4Harkka2.pointDistance(getX(), getY(), x, y)
				< 25*getXScale()*2;
	}

	@Override
	public void onMouseOver()
	{
		/// Balls tend to escape from the mouse
		// Balls that have just been created won't do a thing though
		if (this.lifeTime > this.startLifeTime - 30)
			return;
		
		// Calculates the force direction
		double dir = Studio4Harkka2.pointDirection(getApplet().mouseX,
				getApplet().mouseY, getX(), getY());
		
		System.out.println(PApplet.degrees((float) dir));
		
		// Adds motion
		addDirectionalVelocity(dir, 2);
	}

	@Override
	public void onMousePressed()
	{
		// Does nothing in particular
	}

	@Override
	public void onMouseDown()
	{
		// Doesn't do anything incredibly awesome
	}
	
	@Override
	public void handleParticle()
	{
		// Does everything it would normally do
		super.handleParticle();
		
		// And adds gravitation and lifetime
		addDirectionalVelocity(this.gravityDirection, this.gravityForce);
		this.lifeTime --;
		
		// Also checks collisions with the screen borders (Only if it still has
		// some life left)
		if (this.lifeTime > 0)
		{
			if (getX() < 0 && getHspeed() < 0)
				setVelocity(getHspeed() * (-0.97), getVspeed());
			else if (getX() > getApplet().width && getHspeed() > 0)
				setVelocity(getHspeed() * (-0.97), getVspeed());
			if (getY() < 0 && getVspeed() < 0)
				setVelocity(getHspeed(), getVspeed() * (-0.97));
			else if (getY() > getApplet().height && getVspeed() > 0)
				setVelocity(getHspeed(), getVspeed() * (-0.97));
		}
		
		// Also checks whether the ball should die upon exiting the screen
		// (Only if no life remains)
		else
		{
			if (getApplet() instanceof Studio4Harkka2)
			{
				if (!((Studio4Harkka2) getApplet()).pointIsInWindow(getX(),
						getY(), (int) (-25 * getXScale())))
					kill();
			}
			// If the applet isn't studio4harkka2 then it just dies (because
			// i'm lazy)
			else
				kill();
		}
	}

}
