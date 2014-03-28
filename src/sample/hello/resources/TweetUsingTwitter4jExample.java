package sample.hello.resources;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class TweetUsingTwitter4jExample {
	
	static String consumerKey = "d99MmHjII02ZGBCRoBu8A";
	 
    static String consumerSecret = "rjmcEbTOSFKO5WvApB2RnTax6hKDCNYWlMJvqliHEIc";

    static String accessToken = "384937863-qGQPnqAwhKI24sbhftjAfkA43zRW9HBd7zWM3Jtj";

    static String accessTokenSecret = "RZ5kku2CM2drJsWHOJB3e3jtEBYiCWZp9rttgZGCUJNEL";

	
	public static void main(String[] args) throws IOException, TwitterException {
		
        }
	
	public static JSONArray getTweets() throws IOException, TwitterException, JSONException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setJSONStoreEnabled(true);
		
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		//Instantiate a re-usable and thread-safe factory
	   // TwitterFactory twitterFactory = new TwitterFactory();

	    //Instantiate a new Twitter instance
	   // Twitter twitter = twitterFactory.getInstance();

	    //setup OAuth Consumer Credentials
	    twitter.setOAuthConsumer(consumerKey, consumerSecret);

	    //setup OAuth Access Token
	    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		
	    List<Status> tweets = null;
	    
        JSONArray array = new JSONArray();

	    
		try {
            Query query = new Query("#nyc");
            QueryResult result;
            result = twitter.search(query);
            tweets = result.getTweets();
                        
            
            for (Status tweet : tweets) {
            	
            	JSONObject obj = new JSONObject();
            	
            	obj.put("userName", tweet.getUser().getScreenName());
            	obj.put("text", tweet.getText());
            	obj.put("coordinates", tweet.getGeoLocation());
            	
            	String json = obj.toString();
            	array.put(obj);
            	
            	//String json = DataObjectFactory.getRawJSON(tweet);
            	//String json = "@" + tweet.getUser().getScreenName() + "-" + tweet.getText() + "-" + tweet.getGeoLocation();
            	//System.out.println(json);
                //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getGeoLocation());
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
