package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.Required;
import play.mvc.BodyParser;                     
import play.libs.Json;


import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.node.*;

import views.html.*;
import models.GSMovement;
import models.GSSiteswap;

import models.GSTest;


import java.util.ArrayList;

public class Application extends Controller {
  
  public static class Siteswap {
	  @Required public String ssName;
  }
  
  public static Result gsGrid() {
	  return ok(gsGrid.render());
  }
  
  public static Result jlab() {
	  return redirect("http://jugglinglab.sourceforge.net/bin/JugglingLab.jar");
  }  
  
  public static Result gsSiteswap() {
	JsonNode aJNode= request().body().asJson();
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	ObjectNode result = Json.newObject();
	return ok(gsSiteswap.render( aTrick.asVanillaSiteswap() , aTrick.asJlabHandNotation()));
  }
  
  public static Result ssJson() {
	  ObjectNode result = Json.newObject();
	  GSSiteswap trick = new GSSiteswap();
	  
	  trick.setSsName("Doe");
	  return ok(Json.toJson(trick));
  }
  
  public static Result juggleTest() {
	  JsonNode json = request().body().asJson();
	  return ok(gsSiteswap.render(json.findPath("ss").getTextValue(), json.findPath("hands").getTextValue()));
  }
  
  public static Result juggleDemo() {
	  return ok(juggleDemo.render());
  }
 
  public static Result testTricks() {
	// cascade
	ArrayList<GSTest> tests = new ArrayList<GSTest>();
	GSTest aTest;
	String 	content = "{\"ssName\": \"cascade\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	JsonNode aJNode= Json.parse(content);
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	ObjectNode result = Json.newObject();
	aTest = new GSTest();
	aTest.setName("cascade");
	aTest.setExpVnSs("3");
	aTest.setExpHands("(0)(32).(0)(32).");
	aTest.setaSs(aTrick);
	tests.add(aTest);
	
	// cascade inversé
	content = "{\"ssName\": \"cascade inverse\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"r\",\"catHand\": \"l\",\"catPos\":\"c\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"c\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("cascade inverse");
	aTest.setExpVnSs("3");
	aTest.setExpHands("(32)(0).(32)(0).");
	aTest.setaSs(aTrick);
	tests.add(aTest);
	
	// test half shower
	content = "{\"ssName\": \"half shower\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("half shower");
	aTest.setExpVnSs("3");
	aTest.setExpHands("(32)(0).(0)(32).");
	aTest.setaSs(aTrick);
	tests.add(aTest);
	
	// test windmill
	content = "{\"ssName\": \"windmill\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("windmill");
	aTest.setExpVnSs("3");
	aTest.setExpHands("(-32)(0).(32)(0).");
	aTest.setaSs(aTrick);
	tests.add(aTest);

	// test 4b
	content = "{\"ssName\": \"4b\",\"listMvmt\": [{\"ssBase\": \"4\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"4\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("4b");
	aTest.setExpVnSs("4");
	aTest.setExpHands("(-32)(0).(32)(0).");
	aTest.setaSs(aTrick);
	tests.add(aTest);
	
	return ok(testTricks.render(tests)); 
  }
  
}