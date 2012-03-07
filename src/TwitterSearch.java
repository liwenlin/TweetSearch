import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;

public class TwitterSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 if (args.length == 0) {
		 System.out.println("Please input tag you want to search as parameter");
		 System.exit(-1);
		 }
		Twitter twitter = new TwitterFactory().getInstance();
		try {
			Query q = new Query(args[0]);
			//set the number of tweets to find as 100
			q.setRpp(100);
			QueryResult result = twitter.search(q);
			List<Tweet> tweets = result.getTweets();
			
			for (Tweet tweet : tweets) {
				//if the tweet contains url, return the url
				if (tweet.getURLEntities().length>0) {
					URLEntity[] e = tweet.getURLEntities();
					for (URLEntity u : e) {
						System.out.print(u.getURL().toString() + " ");
					}
					System.out.println();
				}
			}
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

}
