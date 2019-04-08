package de.fraunhofer.iosb.ilt.sta.security;

import de.fraunhofer.iosb.ilt.sta.settings.CoreSettings;
import de.fraunhofer.iosb.ilt.sta.settings.Settings;

public class SecurityManagerFactory {
	
	
	static Class<?> claus=null;
	
	public static SecurityManager createSecurityManager() {
		if (claus==null)
			return null;
		
		SecurityManager sm=null;
		
		try {
			sm=(SecurityManager) claus.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		

		return sm;
		
	}

	public static void init(CoreSettings settings) {
		Settings secSettings=settings.getSecuritySettings();
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
