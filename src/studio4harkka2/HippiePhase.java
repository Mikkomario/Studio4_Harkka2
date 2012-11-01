package studio4harkka2;

public class HippiePhase extends Phase{

	public HippiePhase(Studio4Harkka2 parentApplet){
		super(parentApplet, 4000);
	}

	@Override
	public void onStart() {
		
		getApplet().setVisuals(0, 10, 80, 10, 0.05);
		
		this.addPlacer(new HippiePlacer(this.getApplet()));
		
	}
}
