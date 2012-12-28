package models.blog;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Ebean;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Post extends Model {
	@Id
	public Long id;

	@Constraints.Required
	public String header;
	
	@Constraints.Required
	@Column(columnDefinition="TEXT")
	public String text;
	
	//public Date created;
	
	@OneToMany
	public List<Comment> comments;
	
	public static Model.Finder<Long, Post> find = new Model.Finder<Long, Post>(
			Long.class, Post.class);
	
	public static List<Post> getAllBlogs() {
		List<Post> blogs = new ArrayList<Post>();
		blogs = Ebean.find(Post.class)
				.findList(); 
		return blogs; 
	}
	
}