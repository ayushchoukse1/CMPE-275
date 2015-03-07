package edu.sjsu.cmpe275.lab1.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AccessControl {
	public List<List<Boolean>> accessMatrix =new ArrayList<List<Boolean>>();
	public HashMap<String, HashMap<String, Set<String>>> usersList= new HashMap<String, HashMap<String, Set<String>>>();
	public List<String> owners=new ArrayList<String>();
	public AccessControl(int d){
		
		usersList.put("alice", new HashMap<String, Set<String>>());
		usersList.put("bob", new HashMap<String, Set<String>>());
		usersList.put("carl", new HashMap<String, Set<String>>());
		usersList.get("alice").put("alicetext1", new HashSet<String>());
		usersList.get("alice").put("alicetext2", new HashSet<String>());
		usersList.get("bob").put("bobtext1", new HashSet<String>());
		usersList.get("carl").put("carltext1", new HashSet<String>());
		usersList.get("alice").get("alicetext1").add("alice");
		usersList.get("alice").get("alicetext2").add("alice");
		usersList.get("bob").get("bobtext1").add("bob");
		usersList.get("carl").get("carltext1").add("carl");
		owners.add("alice");
		owners.add("bob");
		owners.add("carl");
	}
	
	public AccessControl(){
	}
	
	


	
}
