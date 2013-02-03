package test;

import static org.junit.Assert.*;

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

import models.GSMovement;
import models.Constantes;

public class TestGSSiteswap {
	String appPath = "c:\\Users\\clem\\Dropbox\\common\\code\\gsWeb\\";
	
	
	@Test
	public void jsonTest() {
		String content;
		File file = new File(appPath+"test\\test\\jsonInput\\john.json");
        try
        {
            content = FileUtils.readFileToString(file);
    		JsonNode aJNode= Json.parse(content);
    		GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
    		assertTrue("Read from json", aTrick.getSsName().equals("John Doe"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }		
	}
	
	@Test
	public void testCascade() {
		String content;
		File file = new File(appPath+"test\\test\\jsonInput\\cascade.json");
        try
        {
            content = FileUtils.readFileToString(file);
    		JsonNode aJNode= Json.parse(content);
    		GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
    		assertEquals("siteswap name", "cascade", aTrick.getSsName());
    		assertEquals("siteswap number of movement", 2, aTrick.getListMvmt().size());
    		assertEquals("catPos", "r", aTrick.getListMvmt().get(1).getCatPos());
    		assertEquals("Cascade hand notation", "(0)(32).(0)(32).", aTrick.asJlabHandNotation());
        } catch (IOException e)
        {
            e.printStackTrace();
        }		
	}
	
	@Test
	public void testCascadeInverse() {
		GSMovement aMove;
		GSSiteswap aTrick = new GSSiteswap();
		aMove = aTrick.newMovement();
		aMove.setSsBase("3");
		aMove.setThrHand("R");
		aMove.setThrPos("r");
		aMove.setCatHand("L");
		aMove.setCatPos("c");
		
		aMove = aTrick.newMovement();
		aMove.setSsBase("3");
		aMove.setThrHand("L");
		aMove.setThrPos("l");
		aMove.setCatHand("R");
		aMove.setCatPos("c");
		assertEquals("Cascade inverse", "(32)(0).(32)(0).", aTrick.asJlabHandNotation());	
	}
	
	@Test
	public void testWindmillLarge() {
		GSMovement aMove;
		GSSiteswap aTrick = new GSSiteswap();
		aMove = aTrick.newMovement();
		aMove.setSsBase("3");
		aMove.setThrHand(Constantes.RIGHT_HAND);
		aMove.setThrPos(Constantes.L_POS);
		aMove.setCatHand(Constantes.LEFT_HAND);
		aMove.setCatPos(Constantes.R_POS);
				
		aMove = aTrick.newMovement();
		aMove.setSsBase("3");
		aMove.setThrHand(Constantes.LEFT_HAND);
		aMove.setThrPos(Constantes.L_POS);
		aMove.setCatHand(Constantes.RIGHT_HAND);
		aMove.setCatPos(Constantes.R_POS);
		assertEquals("Windmill large", "(-32)(32).(32)(-32).", aTrick.asJlabHandNotation());		
	}	
}
