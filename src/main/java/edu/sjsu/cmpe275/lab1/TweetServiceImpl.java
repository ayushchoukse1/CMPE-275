package edu.sjsu.cmpe275.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TweetServiceImpl implements TweetService {

    public static int lengthOfLongestTweetAttempted;

	/***
     * Following is the dummy implementation of methods.
     * Students are expected to complete the actual implementation of these methods as part of lab completion.
     */
//	HashMap<String, ArrayList<String>> tweetMap = new HashMap<String, ArrayList<String>>();
//	ArrayList<String> list = new ArrayList<String>();
//    int lengthOfLongestTweetAttempted=0;
	public void tweet(String user, String message) throws IllegalArgumentException, IOException {
		//updateLengthOfLongestTweetAttempted(message);
		
		System.out.println("user = "+user+ "  message = "+message);
//		if (tweetMap.containsKey(user)) {
//			
//			System.out.println("user present");
//			list = tweetMap.get(user);
//	    	System.out.println("List "+list);
//	    	list.add(message);
//	    	tweetMap.put(user, list);
//		} else {
//			System.out.println("user not present");
//			list.add(message);
//			tweetMap.put(user, list);
//			
//		}
    	/*ArrayList<String> list = tweetMap.get(user);
    	System.out.println(list);
    	list.add(message);
    	tweetMap.put(user, list);*/
    }

    public void follow(String follower, String followee) throws IOException {
    	System.out.println("follower = "+follower+"  followee = "+followee);
    }
    
   
}
