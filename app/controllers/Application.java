package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.Required;
import play.mvc.BodyParser;                     
import play.libs.Json;
import play.libs.Json.*; 
import static play.libs.Json.toJson;
import org.codehaus.jackson.JsonNode;           
import org.codehaus.jackson.node.ObjectNode;  
import views.html.*;

public class Application extends Controller {
  
  public static class Siteswap {
	  @Required public String ssBase;
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
	  return ok(gsGrid.render());
  }
  
  public static Result jlab() {
	  return redirect("http://jugglinglab.sourceforge.net/bin/JugglingLab.jar");
  }  
  
  public static Result gsSiteswap() {
	  JsonNode json = request().body().asJson();
	  if(json == null) {
		    return badRequest("Expecting Json data");
		  } else {
			  String vnSs = json.findPath("vnSiteswap").getTextValue();
			  return ok(gsSiteswap.render(vnSs));	
		  }
//    String name = json.findPath("vnSiteswap").getTextValue();
//    if(name == null) {
//      return badRequest("Missing parameter vnSiteswap");
//    } else {
//    	return ok(gsSiteswap.render(name));
//    }
  }
  
}