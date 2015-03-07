package edu.sjsu.cmpe275.lab1.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		new AccessControl(4);
		ApplicationContext ctx= new ClassPathXmlApplicationContext("Spring.xml");
		FileService fileService=ctx.getBean("fileservice", FileService.class);
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 1");
		fileService.readFile("bob", "home/alice/shared/alicetext1"); 
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 2");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1"); 
		fileService.readFile("bob", "home/alice/shared/alicetext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 3");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 4");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "alice", "home/carl/shared/carltext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 5");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "carl", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 6");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("alice", "carl", "home/alice/shared/alicetext1");
		fileService.shareFile("carl", "bob", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.readFile("bob", "home/alice/shared/alicetext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 7");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 8");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.readFile("bob", "home/alice/shared/alicetext2");
	}

}
