package sample.hello.resources;


import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.TwitterException;

@Path("tweet") //Path to access from URL
public class InterfaceClass {
	
	@POST //Listening for a POST request.
	@Produces(MediaType.TEXT_PLAIN)
	public String reception(String data) throws JSONException { //Accepts String with device location.
		String coordinates = data; //Stores location.
		System.out.println(data);
		JSONArray returnedArray = new JSONArray(); //Array for returninf tweet data.
		
		try {
			returnedArray = APIClass.getTweets(coordinates); //Passes location to APIClass in getTweets() call.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnedArray.toString(); //Return tweet data to device in string format.
	}
	
}
