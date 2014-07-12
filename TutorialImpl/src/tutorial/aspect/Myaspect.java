package tutorial.aspect;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.patterns.ThisOrTargetPointcut;
import org.springframework.aop.framework.AopContext;

/**
 * My Aspect class
 * 
 * @author Nice
 * 
 */
public class Myaspect {
	private Long startTime, endTme;
	private String Finallog;
	private String Methodlog ="***************** Bundle History*********************" +"\n";
	/**
	 * Performed before method call execution
	 */
	public void dobefore(JoinPoint jp) {
		/**
		 * Read in the log file (log.txt) before method execution. 
		 * This will help us determine the security measures to be taken depending on the previous interactions
		 */
		FileReader readLog = null;
		try
		{
			try
			{
				readLog = new FileReader ("log.txt");
				Scanner LogsFile = new Scanner(readLog);
				while (LogsFile.hasNextLine())
				{
					String logRecord = LogsFile.nextLine();
				}
			}
			finally
			{
				//closes the opened files
				if (readLog!=null) readLog.close();
			}
		}
		catch (IOException e)
		{
			System.out.println("file not found");
		}

		//generate the stack trace to get the calling class
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerClassName = null;
		for(int i=0; i<stackTraceElements.length;i++)
		{
			//System.out.println("" + i + stackTraceElements[i].getClassName());
			//if(stackTraceElements[i].getClassName().equals(jp.getThis().getClass().getName()))
			if(stackTraceElements[i].getClassName().equals("sun.reflect.NativeMethodAccessorImpl") &&!(stackTraceElements[i-1].getClassName().equals(jp.getThis().getClass().getName())))
			{
				if(!(stackTraceElements[i-1].getClassName().equals(this.getClass().getName())) &&!(stackTraceElements[i-1].getClassName().equals("sun.reflect.NativeMethodAccessorImpl")))
				{
					System.out.println("The proxy is " + i + stackTraceElements[i].getClassName());
					System.out.println("on stack " + (i-1) + stackTraceElements[i-1].getClassName());
					String className = stackTraceElements[i-1].getClassName();
					Methodlog += "The class Name is: "+ className + "\n";
				}
			}
		}

		startTime = System.currentTimeMillis();// captures the start time of the method call
		String time = "The method started at: " + startTime;
		// System.out.println("" + jp.toString().getClass().getName());
		String signature= " The method signature is: "+ jp.getSignature();
		//System.out.println("The proxy Object is " + jp.getThis().getClass());
		String targetClass = "The target class is: "+ jp.getTarget().getClass().getName();
		Methodlog = Methodlog + String.format(("%4s %8s %10s %n") ,signature + "\n",time + "\n",targetClass + "\n");
	}

	/**
	 * Performed after method call execution
	 */
	public void doafter() {
		endTme = System.currentTimeMillis();
		String ended = "Method ended at: " + endTme;
		String duration = "The method lasted: " + (endTme - startTime)
				+ " Milliseconds";

		Methodlog += String.format(("%4s %4s %n"),ended + "\n",duration + "\n");
		Finallog += Methodlog;
		writetofile();//calls method to write to file the log of all methods.
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args=pjp.getArgs();
		if(args.length>0){
			// captures method arguments 
			String parameters = "";
			for (int i = 0; i < args.length; i++) {
				parameters = "Arguments passed are:"+ args[i];
			}
			Methodlog += parameters+ "\n";// appends the method log
		}
		Object result = pjp.proceed();
		return result;
	}


	public void afterReturning(JoinPoint jp, Object retVal)
	//capture the returned value or null is none was returned.
	{
		String returned = "";
		if (retVal != null){
			System.out.println("Returned " + retVal.toString());
			returned = "Returned: " + retVal.toString();
		}
		else returned = "Returned: Null";
		Methodlog+= returned + "\n";
	}


	public void writetofile()
	{
		try{

			PrintWriter writer = new PrintWriter("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt", "UTF-8");
			writer.println(Finallog);
			writer.close();
		}
		catch (IOException p)
		{
			System.out.print("Not found!");
		}
	}

}