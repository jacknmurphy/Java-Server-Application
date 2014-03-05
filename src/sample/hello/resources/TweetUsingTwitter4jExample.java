package sample.hello.resources;

import java.io.IOException;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetUsingTwitter4jExample {
	
	static String consumerKey = "d99MmHjII02ZGBCRoBu8A";
	 
    static String consumerSecret = "rjmcEbTOSFKO5WvApB2RnTax6hKDCNYWlMJvqliHEIc";

    static String accessToken = "384937863-qGQPnqAwhKI24sbhftjAfkA43zRW9HBd7zWM3Jtj";

    static String accessTokenSecret = "RZ5kku2CM2drJsWHOJB3e3jtEBYiCWZp9rttgZGCUJNEL";

	
	public static void main(String[] args) throws IOException, TwitterException {
		
        }
	
	public static List<Status> getTweets() throws IOException, TwitterException {
		//Instantiate a re-usable and thread-safe factory
	    TwitterFactory twitterFactory = new TwitterFactory();

	    //Instantiate a new Twitter instance
	    Twitter twitter = twitterFactory.getInstance();

	    //setup OAuth Consumer Credentials
	    twitter.setOAuthConsumer(consumerKey, consumerSecret);

	    //setup OAuth Access Token
	    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		
	    List<Status> tweets = null;
	    
		try {
            Query query = new Query("#billmurray");
            QueryResult result;
            result = twitter.search(query);
            tweets = result.getTweets();
            
            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getGeoLocation());
            }
        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
      }
		return tweets;
	}
 
}
