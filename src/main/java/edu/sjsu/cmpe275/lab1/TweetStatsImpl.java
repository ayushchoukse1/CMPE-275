package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TweetStatsImpl implements TweetStats {

	/***
	 * Following is the dummy implementation of methods. Students are expected
	 * to complete the actual implementation of these methods as part of lab
	 * completion.
	 */

	static int lengthOfLongestTweetAttempted = 0;
	public static int attempts = 0;
	public static int followAttempts = 0;
	public static HashMap<String, Integer> LengthMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> mostFollowedMap = new HashMap<String, Integer>();
	ArrayList<String> list = new ArrayList<String>();

	/**
	 * reset all the three measurements.
	 */
	public void resetStats() {
		lengthOfLongestTweetAttempted = 0;
		LengthMap.clear();
		mostFollowedMap.clear();
		followAttempts = 0;
	}

	/**
	 * @return the length of longest message attempted since the beginning or
	 *         last reset. Can be more than 140. If no messages were attempted,
	 *         return 0. Failed messages are counted for this as well.
	 */
	public int getLengthOfLongestTweetAttempted() {
		return lengthOfLongestTweetAttempted;
	}

	/**
	 * @return the user who has been followed by the biggest number of different
	 *         users since the beginning or last reset. If there is a tie,
	 *         return the 1st of such users based on alphabetical order. If the
	 *         follow action did not succeed, it does not count toward the
	 *         stats. If no users were successfully followed, return null.
	 */
	public String getMostFollowedUser() {
		ArrayList<String> listOfMostFollowed = new ArrayList<String>();
		int maxValue;
		if (mostFollowedMap.isEmpty()) {
			maxValue = 0;
		} else {
			// Find maximum value.
			maxValue = Collections.max(mostFollowedMap.values());
		}
		/*
		 * Iterate through HashMap to find the key that has value equal to the
		 * maxValue.
		 */
		for (Entry<String, Integer> entry : mostFollowedMap.entrySet()) {
			if (entry.getValue() == maxValue) {
				listOfMostFollowed.add(entry.getKey());
			}
		}

		if (listOfMostFollowed.isEmpty()) {
			return null;
		} else {
			// Sort the list alphabetically to return head element in case of
			// tie.
			Collections.sort(listOfMostFollowed);
			return listOfMostFollowed.get(0);
		}
	}

	/**
	 * The most productive user is determined by the total length of all the
	 * messages successfully tweeted since the beginning or last reset. If there
	 * is a tie, return the 1st of such users based on alphabetical order. If no
	 * users successfully tweeted, return null.
	 * 
	 * @return the most productive user.
	 */
	public String getMostProductiveUser() {

		int maxValue;
		if (LengthMap.isEmpty()) {
			maxValue = 0;
		} else {
			maxValue = Collections.max(LengthMap.values());
		}
		for (Entry<String, Integer> entry : LengthMap.entrySet()) {
			if (entry.getValue() == maxValue) {
				list.add(entry.getKey());
			}
		}

		if (list.isEmpty()) {
			return null;
		} else {
			Collections.sort(list);
			return list.get(0);
		}
	}

	/*
	 * Checks the length of current tweet with 'lengthOfLongestTweetAttempted'.
	 * If the new tweet length is greater than lengthOfLongestTweetAttempted
	 * then it updates the lengthOfLongestTweetAttempted with new length.
	 */
	public static void updateLengthOfLongestTweetAttempted(String message) {
		if (message.length() > lengthOfLongestTweetAttempted) {
			lengthOfLongestTweetAttempted = message.length();
		}
	}

}
