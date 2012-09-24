package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import play.*;
import play.mvc.*;
import play.libs.Json;

import models.GSSiteswap;

import org.junit.Test;



import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.node.*;

public class TestGSSiteswap {
	
	@Test
	public void basicTest() {
		String refSs;
		String aStr = new String("test");
		GSSiteswap aSs = new GSSiteswap();
		refSs = aSs.getGeneralizedSiteswap(); 
	    assertTrue(aStr.equals(refSs));		
	}
	
	@Test
	public void jsonTest() {
		File aFile = new File("cascade.json");
		aFile.toString();
		GSSiteswap aTrick = Json.fromJson( Json.parse("{\"ssBase\":\"Doe\",\"listMvmt\":[{\"thrHand\":\"r\",\"thrPos\":\"c\"},{\"thrHand\":\"l\",\"thrPos\":\"c\"}]}"), GSSiteswap.class);
		//GSSiteswap aTrick = Json.fromJson( Json.parse(Json.stringify ), GSSiteswap.class);
		assertTrue(aTrick.getSsBase().equals("Doe"));
		//toJson(nameStruct);
	}
}
