package sample.hello.resources;

import java.io.IOException;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class APIClass {
	
	static String consumerKey = "d99MmHjII02ZGBCRoBu8A";
	 
    static String consumerSecret = "rjmcEbTOSFKO5WvApB2RnTax6hKDCNYWlMJvqliHEIc";

    static String accessToken = "384937863-qGQPnqAwhKI24sbhftjAfkA43zRW9HBd7zWM3Jtj";

    static String accessTokenSecret = "RZ5kku2CM2drJsWHOJB3e3jtEBYiCWZp9rttgZGCUJNEL";
    
	
	public static void main(String[] args) throws IOException, TwitterException {
		
        }
	
	public static JSONArray getTweets(String coordinates) throws IOException, TwitterException, JSONException { //Method that contacts twitter.
		ConfigurationBuilder cb = new ConfigurationBuilder(); //Configuration builder for bulding an instance of TwtterFactory.
		cb.setJSONStoreEnabled(true); //Allow JSON
		
		Twitter twitter = new TwitterFactory(cb.build()).getInstance(); //Create TwitterFactory object.
		
	    twitter.setOAuthConsumer(consumerKey, consumerSecret); //Set OAuth keys for twitter security.

	    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		
	    List<Status> tweets = null;
	    
	    String receivedCoordinates = coordinates;
	    System.out.println(receivedCoordinates);
	    
	    String[] modify = receivedCoordinates.split(",", 2);
	    String lat = modify[0];
	    String lon = modify[1];
	    
	    System.out.println(lat);
	    System.out.println(lon);
	    
	    Double lat2 = Double.parseDouble(lat);
	    Double lon2 = Double.parseDouble(lon);
	    
	    System.out.println(lat2);
	    System.out.println(lon2);
	    
	    GeoLocation loc = new GeoLocation(lat2, lon2);
	    	    
        JSONArray array = new JSONArray();
        
	    
		try {
            Query query = new Query();
            query.setGeoCode(loc, 1, query.KILOMETERS );

            QueryResult result;
            result = twitter.search(query);
            tweets = result.getTweets();
                        
            
            for (Status tweet : tweets) {
            	            	
            	JSONObject obj = new JSONObject();
            	
            	obj.put("userName", tweet.getUser().getScreenName());
            	obj.put("text", tweet.getText());
            	final GeoLocation location = tweet.getGeoLocation();
            	if(location != null){
            		obj.put("lat", location.getLatitude());
            		obj.put("lon", location.getLongitude());
            	}
            	
            	String json = obj.toString();
            	array.put(obj);
            	
            	System.out.println(json);
            }
            System.out.println(array);
        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
      }
		return array;
	}
 
}
