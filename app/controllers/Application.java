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

import com.avaje.ebean.Ebean;

import views.html.*;
import models.GSMovement;
import models.GSSiteswap;

import models.GSTest;


import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
  
  public static class Siteswap {
	  @Required public String ssName;
  }
  
  public static Result gsGrid() {
	  List<GSSiteswap> listTricks = GSSiteswap.getAllTricks();
	  return ok(gsGrid.render(listTricks));
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
	String 	content = "{\"ssName\": \"cascade\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"c\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"c\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	JsonNode aJNode= Json.parse(content);
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	ObjectNode result = Json.newObject();
	aTest = new GSTest();
	aTest.setName("cascade");
	aTest.setExpVnSs("3");
	aTest.setExpHands("(0)(32).(0)(32).");
	aTest.setaSs(aTrick);
	tests.add(aTest);
	
	return ok(testTricks.render(tests)); 
  }
  
  public static Result gsSave() {	
	JsonNode aJNode= request().body().asJson();
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	Ebean.save(aTrick);  
	return ok("id:"+ aTrick.id);
  }
  
  public static Result loadListTricks() {
	  List<GSSiteswap> listTtricks = GSSiteswap.getAllTricks();
	  return ok(listTricks.render(listTtricks));
  }
  
  public static Result loadTrick(long id) {
	  GSSiteswap aTrick = GSSiteswap.find.byId(id);
	  if (aTrick != null) { 
			return ok(gsSiteswap.render(aTrick.asVanillaSiteswap() , aTrick.asJlabHandNotation()));

	  } else {
		  return badRequest("Erreur lors du chargement");
	  }
  }
  
}