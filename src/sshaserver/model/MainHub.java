package sshaserver.model;

import sshaserver.view.ActivityView;
import sshaserver.view.MainView;
import sshaserver.view.SettingsView;

public class MainHub {

	public static final int nbrOfClients = 4;
	public static final int port = 6666;
	public static final int globalSleep = 1;
	public static final String osName = System.getProperty("os.name").toLowerCase().replaceAll("\\s", "");
	public static final String macSavepath = "/Users/bellevik/Documents/sshaserver";

	private static MainHub myControl = null;
	
	private MainView mainView;
	private SettingsView settingsView;
	private ActivityView activityView;
	
	public static MainHub getController() {
		if (myControl == null) {
			myControl = new MainHub();
		}
	   return myControl;
	}
	
	public MainView getMainView() {
		return mainView;
	}
	
	public ActivityView getActivityView() {
		return activityView;
	}
	
	public void setSettingsView(SettingsView settingsView) {
		this.settingsView = settingsView;
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public void setActivityView(ActivityView activityView) {
		this.activityView = activityView;
	}
	
	public SettingsView getSettingsView() {
		return settingsView;
	}
}
