package de.fraunhofer.iosb.ilt.sta.security.pgjooq;

import org.jooq.Record;
import org.jooq.Condition;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityManager implements de.fraunhofer.iosb.ilt.sta.security.SecurityManager {

	String username;
	
	@Override
	public boolean init(Object req, Object resp) {
		HttpServletRequest request=(HttpServletRequest) req;
		List<String> headerNames=java.util.Collections.list(request.getHeaderNames());
		String authorization=request.getHeader("authorization");

		HttpServletResponse response=(HttpServletResponse) resp;
		
		if (authorization==null) {
			String header="Basic realm='Use any station name as login'";
			response.addHeader("WWW-Authenticate", header);
			response.setStatus(401);
			return true;
		}

		String[] parts=authorization.split("\\s+");
		String up64=parts[1];
		Decoder decoder=Base64.getDecoder();
		String up="";
		try {
			up = new String(decoder.decode(up64), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();	// Shouldn't happen unless the author of this lines epically failed :-)
		}
		String[] u_p=up.split(":");

		username=u_p[0];
		String password=u_p[1];


		return false;
	}
	
	
	public SelectJoinStep<Record> addGetJoins(SelectJoinStep<Record> existingQuery) {
		return existingQuery;
	}

	public SelectConditionStep<Record> addGetWhere(SelectConditionStep<Record> whereStep) {
		SelectConditionStep<Record> newWhere=whereStep.and("\"NAME\"=?", this.username);
		return newWhere;
	}


}
