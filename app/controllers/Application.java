package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.Required;

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
  
}