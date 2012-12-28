package controllers.blog;

import java.util.List;

import models.blog.*;


import play.*;
import play.mvc.*;
import play.api.templates.Html;
import views.html.*;
import play.data.Form;

import org.pegdown.*;

public class Blog extends Controller {
    /**
     * Login page.
     */
		
    public static Result log() {
        return ok(views.html.blog.login.render(Form.form(models.blog.Login.class)));
    }	

    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<models.blog.Login> loginForm = Form.form(models.blog.Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(views.html.blog.login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            return redirect(
            	controllers.blog.routes.Admin.index()
            );
        }
    }
    
    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
        	controllers.blog.routes.Blog.index()
        );
    }    
    
	public static Result index() {
		PegDownProcessor processor = new PegDownProcessor();		
		List<Post> blogs = Post.getAllBlogs();
		return ok(RendererManager.show(Configuration.root().getString("theme") , blogs, processor));
	}
	
	public static Result show(Long id) {
		PegDownProcessor processor = new PegDownProcessor();		
		  Post aPost = Post.find.byId(id);
		  return ok(RendererManager.showPost(Configuration.root().getString("theme") , aPost, processor));
	}	

}