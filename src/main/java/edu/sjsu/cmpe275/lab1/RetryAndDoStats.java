package edu.sjsu.cmpe275.lab1;

import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.sound.midi.Track;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class RetryAndDoStats implements MethodInterceptor {
	/***
	 * Following is the dummy implementation of advice. Students are expected to
	 * complete the required implementation as part of lab completion.
	 */
	HashMap<String, ArrayList<String>> tweetMap = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> followMap = new HashMap<String, ArrayList<String>>();
	ArrayList<String> list = new ArrayList<String>();
	int lengthOfLongestTweetAttempted = 0;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		switch (methodName) {
		case "follow":
			// Get arguments from the method and convert them from Object[] to
			// String[].
			String[] followArguments = Arrays.copyOf(invocation.getArguments(), invocation.getArguments().length,
					String[].class);
			String follower = followArguments[0];
			String followee = followArguments[1];
			try {
				// TweetService method "follow" runs now.
				Object result = invocation.proceed();
				putIntoHashMap(followMap, follower, followee);
				putIntoNumberHashMap(TweetStatsImpl.mostFollowedMap, followee, 1);
				return result;
			} catch (IOException e) {
				/**
				 * Attempts to reconnect three times in case of network failure.
				 * If still doesn't gets through throws IOException
				 */
				IOExceptionHandler(TweetStatsImpl.followAttempts, invocation);	
			}
		case "tweet":
			// Get arguments from the method and convert them from Object[] to
			// String[].
			String[] arguments = Arrays.copyOf(invocation.getArguments(), invocation.getArguments().length,
					String[].class);
			Boolean lengthCondition = checkTweetLength(arguments[1]);
			String user = arguments[0];
			String message = arguments[1];
			// Update length of max tweet attempted even if the tweet got
			// failed.
			TweetStatsImpl.updateLengthOfLongestTweetAttempted(message);
			if (lengthCondition) {
				try {
					// TweetService method "tweet" runs now.
					Object result = invocation.proceed();
					// update tweets into hashmap
					putIntoHashMap(tweetMap, user, message);
					// update max length of tweets by that user.
					putIntoNumberHashMap(TweetStatsImpl.LengthMap, user, message.length());
					return result;
				} catch (IOException e) {

					/**
					 * Attempts to reconnect three times in case of network
					 * failure. If still doesn't gets through throws IOException
					 */
					IOExceptionHandler(TweetStatsImpl.attempts, invocation);
				}
			} else {

				/**
				 * @throws IllegalArgumentException
				 *             if the message is more than 140 characters as
				 *             measured by string length.
				 */
				throw new IllegalArgumentException("Tweet length exceeds 140 characters");
			}
		default:

			/**
			 * default method in case method doesn't match.
			 * 
			 * @throws IllegalArgumentException
			 *             If the specified method name is incorrect.
			 * 
			 */
			throw new IllegalArgumentException("Invalid method name " + methodName);
		}
	}

	/*
	 * This method checks the length of the tweet attempted. If the length of
	 * the tweet is larger than 140 characters then it sets the boolean false
	 * else returns true.
	 */
	public Boolean checkTweetLength(String tweet) {
		if (tweet.length() > 140) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * This method takes a hashMap, key and Value as parameters and adds them
	 * into the hashmap. If the key(User/Follwer) is already present in the
	 * hashmap then updates the value(ArrayList-Tweet/Followee)
	 */
	public void putIntoHashMap(HashMap<String, ArrayList<String>> hashMap, String key, String value) {
		ArrayList<String> hashMapList = new ArrayList<String>();
		if (hashMap.containsKey(key)) {
			hashMapList = tweetMap.get(key);
			hashMapList.add(value);
			tweetMap.put(key, hashMapList);
		} else {
			hashMapList.add(value);
			tweetMap.put(key, hashMapList);
		}
	}

	/*
	 * This method takes a hashMap, key and length as parameters and adds them
	 * into the hashmap. If the key(User/Followee) is already present in the
	 * hashmap then it updates the value(Integer-Tweet length/Number of
	 * followers).
	 */
	public void putIntoNumberHashMap(HashMap<String, Integer> hashMap, String key, int length) {
		if (hashMap.containsKey(key)) {
			int temp = hashMap.get(key);
			temp = temp + length;
			hashMap.put(key, temp);
		} else {
			hashMap.put(key, length);
		}
	}

	/*
	 * This method handles the IOException when there is a network failure. It
	 * attempts to reconnect to the network. If the network connection still
	 * fails after 3 attempts,it generates IOException.
	 */
	public void IOExceptionHandler(int count, MethodInvocation invocation) throws Throwable {
		if (count < 3) {
			count += 1;
			System.out.println("Network Error attempting to connect, Attempt: " + count);
			invoke(invocation);
		} else {
			count = 0;
			throw new IOException("Network Error. Cannot connect to network");
		}
	}

}
