package edu.sjsu.cmpe275.lab1.aspect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.lab1.UnauthorizedException;
import edu.sjsu.cmpe275.lab1.models.AliceText1;
import edu.sjsu.cmpe275.lab1.service.AccessControl;
import edu.sjsu.cmpe275.lab1.service.FileService;

@Aspect
public class FileServiceAspect {
	AliceText1 alicefile =new AliceText1();
	AccessControl ac=new AccessControl(3);
	
	
@Before("execution(public void shareFile(..))")
	void updateTableAdvice(JoinPoint joinPoint){
		
	
		FileService fileService = (FileService)joinPoint.getTarget();
		
		Object[] param= joinPoint.getArgs();
		String user=param[0].toString();
		String targetuser=param[1].toString();
		String filepath=param[2].toString();
		String[] str1=filepath.split("/");
		String owner=str1[1];
		String ownerfile=str1[3];
		
		
		if (!ac.usersList.get(owner).get(ownerfile).contains(user)) {
			System.out.println(user+" Unauthorized !!");
			throw new UnauthorizedException();	
			
			}else{
				ac.usersList.get(owner).get(ownerfile).add(targetuser);
				System.out.println(user+ "  gave access of "+ownerfile+" to "+targetuser);
			}
}		
		
@Before("execution(public void unshareFile(..))")
void unshareAdvice(JoinPoint joinPoint){
	

	FileService fileService = (FileService)joinPoint.getTarget();
	
	Object[] param= joinPoint.getArgs();
	String user=param[0].toString();
	String targetuser=param[1].toString();
	String filepath=param[2].toString();
	String[] str1=filepath.split("/");
	String owner=str1[1];
	String ownerfile=str1[3];

	if (!ac.usersList.get(owner).get(ownerfile).contains(targetuser)) {
		System.out.println("User not present");
		//System.out.println("Access to the file already Granted");
	}else{
		ac.usersList.get(owner).get(ownerfile).remove(targetuser);
		System.out.println(owner+" unshared "+ownerfile+" from "+targetuser);
	}

	
} 



	
@Before("execution(public byte[] readFile(..))")
void readAdvice(JoinPoint joinPoint){

	Object[] param= joinPoint.getArgs();
	String targetuser=param[0].toString();
	String filepath=param[1].toString();
	String[] str1=filepath.split("/");
	String owner=str1[1];
	String ownerfile=str1[3];
	
	

	if(ac.usersList.get(owner).get(ownerfile).contains(targetuser)){
		System.out.println(targetuser+" reads the "+ ownerfile);

	}else{
		System.out.println(targetuser+" cannot Read "+ownerfile);
		throw new UnauthorizedException();	
	}
	

} 
	
		
	}


