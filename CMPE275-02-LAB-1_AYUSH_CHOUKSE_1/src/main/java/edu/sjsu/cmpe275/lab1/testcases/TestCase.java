package edu.sjsu.cmpe275.lab1.testcases;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.lab1.UnauthorizedException;
import edu.sjsu.cmpe275.lab1.service.AccessControl;
import edu.sjsu.cmpe275.lab1.service.FileService;

public class TestCase {
	
	AccessControl ac=new AccessControl(4);
	ApplicationContext ctx= new ClassPathXmlApplicationContext("Spring.xml");
	FileService fileService=ctx.getBean("fileservice", FileService.class);
	@Test(expected = UnauthorizedException.class)
	public void test1() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 1");
		fileService.readFile("bob", "home/alice/shared/alicetext1");
	}
	@Test
	public void test2() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 2");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1"); 
		fileService.readFile("bob", "home/alice/shared/alicetext1");
	}

	@Test
	public void test3() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 3");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
	}

	@Test(expected = UnauthorizedException.class)
	public void test4() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 4");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "alice", "home/carl/shared/carltext1");
	}

	@Test(expected = UnauthorizedException.class)
	public void test5() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 5");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "carl", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
		
	}

	@Test(expected = UnauthorizedException.class)
	public void test6() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 6");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("alice", "carl", "home/alice/shared/alicetext1");
		fileService.shareFile("carl", "bob", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.readFile("bob", "home/alice/shared/alicetext1");
	}

	@Test(expected = UnauthorizedException.class)
	public void test7() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 7");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		fileService.unshareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.readFile("carl", "home/alice/shared/alicetext1");
		fileService.shareFile("bob", "carl", "home/alice/shared/alicetext1");
		
	}
	@Test(expected = UnauthorizedException.class)
	public void test8() {
		
		System.out.println("---------==========--------------============-----------------");
		System.out.println("Test Case : 8");
		fileService.shareFile("alice", "bob", "home/alice/shared/alicetext1");
		fileService.readFile("bob", "home/alice/shared/alicetext2");
	}


	
	
}
