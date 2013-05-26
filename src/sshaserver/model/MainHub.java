package sshaserver.model;

import sshaserver.view.MainView;

public class MainHub {

	public static final int nbrOfClients = 4;
	public static final int port = 6666;
	public static final int globalSleep = 1;
	public static final String osName = System.getProperty("os.name").toLowerCase().replaceAll("\\s", "");
	public static final String macSavepath = "/Users/bellevik/Documents/sshaserver";

	private static MainHub myControl = null;
	
	private MainView mainView;
	
	public static MainHub getController() {
		if (myControl == null) {
			myControl = new MainHub();
			// prepare myController here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}
	
	public MainView getMainView() {
		return mainView;
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	
}
