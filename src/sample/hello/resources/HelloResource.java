package sample.hello.resources;


import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter4j.Status;
import twitter4j.TwitterException;

@Path("/hello")
public class HelloResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Jersey";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() 
	{
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	  // This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() 
	{
	
		List<Status> returnedTweets = null;
		String convert = null;
		
		try {
			returnedTweets = TweetUsingTwitter4jExample.getTweets();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Status tweet : returnedTweets) {
            convert = "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getCreatedAt() + "</body></h1>" + "</html> ";
        
        }
		return convert;
	}
}
