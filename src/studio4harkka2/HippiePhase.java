package studio4harkka2;

/**
 *  This class represents a hippie-flower phase in the program's flow.
 * 	Creates HippiePlacer to the applet.
 * 
 * @author Tiitu
 *
 */
public class HippiePhase extends Phase{

	/**
	 * Constructor
	 * 
	 * @param parentApplet defines the parent applet of this phase
	 */
	public HippiePhase(Studio4Harkka2 parentApplet){
		super(parentApplet, 4000);
	}

	/**
	 * Sets the visuals of the applet and adds a hippie-flower placer
	 */
	@Override
	public void onStart() {
		
		getApplet().setVisuals(0, 20, 100, 5, 0.2);
		
		this.addPlacer(new HippiePlacer(this.getApplet()));
		
	}
}
