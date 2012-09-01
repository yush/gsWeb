package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }
  
  public static Result juggle() {
	  return ok(juggle.render());
  }
  
  public static Result staticPage() {
	  return ok(staticPage.render());
  }
  
}