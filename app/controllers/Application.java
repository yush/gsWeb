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
	
  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }
  
  public static Result juggle() {
	  return ok(juggle.render());
  }
  
  public static Result basicForm() {
	  Form<Siteswap> siteswapForm = form(Siteswap.class); 
	  return ok(basicForm.render(siteswapForm));
  }
  
  public static Result responseBasicForm() {
	  Form<Siteswap> siteswapForm = form(Siteswap.class).bindFromRequest();
	  if ( !siteswapForm.hasErrors()) {
		  return ok(responseBasicForm.render(siteswapForm));
	  } else {
		  return badRequest(basicForm.render(siteswapForm));
	  }
  }
  
  public static Result gsGrid() {
	  ObjectMapper mapper = new ObjectMapper();
	  return ok(gsGrid.render());
  }
  
  public static Result jlab() {
	  return redirect("http://jugglinglab.sourceforge.net/bin/JugglingLab.jar");
  }  
  
  public static Result gsSiteswap() {
	//String 	content = "{\"ssName\": \"cascade\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"r\",\"catHand\": \"l\",\"catPos\":\"c\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"c\"}]}";
//	JsonNode aJNode= Json.parse(content);
	JsonNode aJNode= request().body().asJson();
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	ObjectNode result = Json.newObject();
	String calc = aTrick.asJlabHandNotation();	  
	return ok(gsSiteswap.render(calc));
  }
  
  public static Result ssJson() {
	  ObjectNode result = Json.newObject();
	  GSSiteswap trick = new GSSiteswap();
	  
	  trick.setSsName("Doe");
	  return ok(Json.toJson(trick));
  }
  
  public static Result testTricks() {
	// cascade
	ArrayList<GSTest> tests = new ArrayList<GSTest>();
	GSTest aTest;
	String 	content = "{\"ssName\": \"half shower\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	JsonNode aJNode= Json.parse(content);
	GSSiteswap aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	ObjectNode result = Json.newObject();
	aTest = new GSTest();
	aTest.setName("cascade");
	aTest.setaTest("(0)(32).(0)(32).");
	aTest.setaRes(aTrick.asJlabHandNotation());
	tests.add(aTest);
	
	// cascade inversé
	content = "{\"ssName\": \"cascade\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"r\",\"catHand\": \"l\",\"catPos\":\"c\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"c\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("cascade inverse");
	aTest.setaTest("(32)(0).(32)(0).");
	aTest.setaRes(aTrick.asJlabHandNotation());
	tests.add(aTest);
	
	// test half shower
	content = "{\"ssName\": \"half shower\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("half shower");
	aTest.setaTest("(32)(0).(0)(32).");
	aTest.setaRes(aTrick.asJlabHandNotation());
	tests.add(aTest);
	
	// test windmill
	content = "{\"ssName\": \"windmill\",\"listMvmt\": [{\"ssBase\": \"3\",\"thrHand\": \"r\",\"thrPos\": \"l\",\"catHand\": \"l\",\"catPos\": \"r\"}, {\"ssBase\": \"3\",\"thrHand\": \"l\",\"thrPos\": \"l\",\"catHand\": \"r\",\"catPos\": \"r\"}]}";
	aJNode= Json.parse(content);
	aTrick = Json.fromJson(aJNode, GSSiteswap.class);
	aTest = new GSTest();
	aTest.setName("windmill");
	aTest.setaTest("(-32)(0).(32)(0).");
	aTest.setaRes(aTrick.asJlabHandNotation());
	tests.add(aTest);

	return ok(testTricks.render(tests)); 
  }
  
}