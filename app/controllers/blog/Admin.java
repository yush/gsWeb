package controllers.blog;

import java.util.List;

import models.blog.Post;
import play.*;
import play.mvc.*;
import com.avaje.ebean.Ebean;
import play.db.ebean.*;
import views.html.blog.adminViews.*;
import play.data.Form;
import org.pegdown.*;

@Security.Authenticated(Secured.class)
public class Admin extends Controller {

	public static Result index() {
		if (Secured.isAuthorized()) {
			List<Post> blogs = Post.getAllBlogs();
			return ok(views.html.blog.adminViews.indexAdmin.render(blogs));
		} else {
			return redirect(controllers.blog.routes.Blog.log());
		}
	}

	public static Result newPost() {
		Post aNewPost = new Post();
		aNewPost.header = "";
		aNewPost.text = "";
		Form<Post> blogForm = Form.form(Post.class);
		blogForm = blogForm.fill(aNewPost);
		return ok(views.html.blog.adminViews.postForm.render(blogForm));
	}

	public static Result submitNewPost() {
		Form<Post> blogForm = Form.form(Post.class);
		Post aBlog = blogForm.bindFromRequest().get();
		Ebean.save(aBlog);
		return index();
	}

	public static Result submitUpdatePost(Long id) {
		Form<Post> blogForm = Form.form(Post.class);
		Post aBlog = blogForm.bindFromRequest().get();
		aBlog = Post.find.byId(id);
		if (aBlog != null) {
			aBlog.id = id;
			aBlog.text = "test";
			aBlog.update();			
		}
		return redirect(controllers.blog.routes.Admin.index());
	}

	public static Result updatePost(Long id) {
		Post aPost = Post.find.byId(id);
		if (aPost != null) {						
			Form<Post> formPost = Form.form(Post.class);
			formPost = formPost.fill(aPost);
			return ok(views.html.blog.adminViews.postForm.render(formPost));
		} else {
			return badRequest("erreur");
		}
	}

	public static Result deletePost(Long id) {
		Post aPost = Post.find.byId(id);
		if (aPost != null) {
			Ebean.delete(aPost);
			return redirect(controllers.blog.routes.Admin.index());
		} else {
			return badRequest("erreur");
		}

	}


}