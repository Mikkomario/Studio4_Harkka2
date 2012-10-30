package studio4harkka2;

import processing.core.PApplet;

public class Flower extends Particle{

	public Flower(int newx, int newy, double maxVelocity, double friction,
			double maxRotation, double rotationFriction,
			double xscale, double yscale, Placer parentPlacer, 
			PApplet parentApplet){
		super(newx, newy, maxVelocity, friction, maxRotation, rotationFriction,
				xscale, yscale, 20, parentPlacer, parentApplet);
	}

	@Override
	public void drawSelf() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean positionIsOver(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMouseOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDown() {
		// TODO Auto-generated method stub
		
	}
}
