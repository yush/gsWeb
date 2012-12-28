package controllers.blog;

import java.util.List;
import org.pegdown.PegDownProcessor;

import models.blog.Post;
import play.*;
import play.mvc.*;
import views.html.blog.defaultTheme.*;

public class RendererManager extends Controller {
	public static Content show(String theme, List<Post> posts, PegDownProcessor processor) {
		if (theme.equals("evolved")) {
			return views.html.blog.evolved.index.render(posts, processor);
		} else {
			return views.html.blog.defaultTheme.index.render(posts, processor);		
		} 
	}
	
	public static Content showPost(String theme, Post aPost, PegDownProcessor processor) {
		if (theme.equals("evolved")) {
			return views.html.blog.evolved.post.render(aPost, processor);
		} else {
			return views.html.blog.defaultTheme.post.render(aPost, processor);		
		} 
	}	
}