package de.fraunhofer.iosb.ilt.sta.security;


import de.fraunhofer.iosb.ilt.frostserver.settings.CoreSettings;
import de.fraunhofer.iosb.ilt.frostserver.settings.Settings;

public class AccessManagerFactory {
	
	
	static Class<?> claus=null;
	
	public static AccessManager createAccessManager() {
		if (claus==null)
			return null;
		
		AccessManager am=null;
		
		try {
			am=(AccessManager) claus.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		

		return am;
		
	}

	public static void init(CoreSettings settings) {
		Settings secSettings=settings.getSecuritySettings();	// TODO: Rename this method
		String implementationClassName=secSettings.get("ImplementationClassName");
		
		if (implementationClassName==null || implementationClassName.isEmpty())
			return;
		
		
		try {
			claus = Class.forName(implementationClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		
	}
}
