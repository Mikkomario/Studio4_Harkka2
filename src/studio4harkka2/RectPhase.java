package studio4harkka2;

/**
 * This class represents a rectangle phase in the program's flow
 * Creates a RectPhase to the applet
 * 
 * @author Tiitu
 *
 */
public class RectPhase extends Phase{

	/**
	 * Constructor
	 * 
	 * @param parentApplet defines the parent applet of this phase
	 */
	public RectPhase(Studio4Harkka2 parentApplet){
		super(parentApplet, 4000);
	}

	/**
	 * Sets the visuals of the applet and adds a rectangle placer
	 */
	@Override
	public void onStart() {
		
		getApplet().setVisuals(0, 15, 95, 20, 0.05);
		
		this.addPlacer(new RectPlacer(this.getApplet()));
		
	}
}
