package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;

import play.*;
import play.mvc.*;
import play.libs.Json;

import models.GSSiteswap;

import org.junit.Test;



import org.codehaus.jackson.*;
import org.codehaus.jackson.map.ObjectMapper;
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
		String content;
		File file = new File("c:\\Users\\clem\\Dropbox\\common\\code\\gsWeb\\test\\test\\jsonInput\\cascade.json");
        try
        {
            content = FileUtils.readFileToString(file);
            System.out.println("File content: " + content);
    		JsonNode aJNode= Json.parse(content);
    		GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
    		assertTrue(aTrick.getSsBase().equals("John Doe"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }		
		

	}
}
