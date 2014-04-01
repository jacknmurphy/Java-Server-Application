package sample.hello.resources;


import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter4j.TwitterException;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;

@Path("hello")
public class HelloResource {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(String data) throws JSONException {
		String coordinates = data;
		System.out.println(data);
		JSONArray returnedArray = new JSONArray();
		
		try {
			returnedArray = TweetUsingTwitter4jExample.getTweets(coordinates);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnedArray.toString();
	}
	
}
