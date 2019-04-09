package de.fraunhofer.iosb.ilt.sta.security;

public interface AccessManager {

	/**
	 * This will be called at the start of each request.
	 * The purpose is to check, whether all necessary data is present (e.g. Auth headers).
	 * The call may manipulate the response (return true in the case) to signal something to the browser (like a forward to a login page or a response code requesting a login).
	 * @param request
	 * @param reponse
	 */
	boolean init(Object request, Object reponse);	// TODO: Make this being an HttpServletRequest

}
