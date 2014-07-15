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

import tutorial.policies.PolicyAnalysis;

/**
 * My Aspect class
 * 
 * @author Nice
 * 
 */
public class Myaspect {
	private Long startTime, endTme;
	private String Finallog;
	private String Methodlog = "";
	private PolicyAnalysis myAnalysis = new PolicyAnalysis();

	/**
	 * Performed before method call execution
	 * 
	 * Before method execution. load policies say from a file one after the other :P
	 * 
	 */
	public void dobefore(JoinPoint jp) {
		// generate the stack trace to get the calling class
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		for (int i = 0; i < stackTraceElements.length; i++) {

			if (stackTraceElements[i].getClassName().equals("sun.reflect.NativeMethodAccessorImpl")
					&& !(stackTraceElements[i - 1].getClassName().equals(jp.getThis().getClass().getName()))) {
				if (!(stackTraceElements[i - 1].getClassName().equals(this.getClass().getName()))
						&& !(stackTraceElements[i - 1].getClassName().equals("sun.reflect.NativeMethodAccessorImpl"))) {
					System.out.println("The proxy is " + i
							+ stackTraceElements[i].getClassName());
					
				System.out.println("on stack " + (i - 1)+ stackTraceElements[i - 1].getClassName());
					
				// Try to get the client Object to access the client
				
					String className = stackTraceElements[i - 1].getClassName();
					Methodlog += "Name:" + className + ",";
					
				}
			}
		}
		startTime = System.currentTimeMillis();// captures the start time of the method call
		String time = "started:" + startTime;
		// System.out.println("" + jp.toString().getClass().getName());
		String signature = "methodSignature:" + jp.getSignature();
		String name = "methodName"+ jp.getSignature().getName();
		// System.out.println("The proxy Object is " + jp.getThis().getClass());
		String targetClass = "targetClass:"
				+ jp.getTarget().getClass().getName();
		Methodlog = Methodlog + name+ "," +  signature + ","+  time	+ ","+ targetClass +",";
	}

	/**
	 * Performed after method call execution
	 */
	public void doafter() {

		endTme = System.currentTimeMillis();
		String ended = "MethodEnded " + endTme;
		String duration = "Duration " + (endTme - startTime)
				+ " Milliseconds";
		Methodlog += ended + duration;
		Finallog += Methodlog;
		myAnalysis.writetoFile(Finallog);// calls method to write to file the log of all methods.
	}

	/**
	 * Gets the method arguments being passed and calls the analysis class to
	 * check if they have been violated.
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result= null;
		Object[] args = pjp.getArgs();
		String parameters = "";
		if (args.length > 0) {
			// captures method arguments
			for (int i = 0; i < args.length; i++) {
				parameters = "argumentsPassed " + args[i];
			}
			Methodlog += parameters;// appends the method log
		}
		String passedMethod = pjp.getSignature().getName();
		myAnalysis.readPolicy();// read in the policies before the method executes.
		boolean deciding = myAnalysis.Analyse(passedMethod, parameters);
		
		if (deciding) {// Unacceptable level; throw exception to stop method execution
				throw new Throwable();
				
		} 
		else {// no violation proceed with method execution
			result = pjp.proceed();
		}
		return result;
	}
	/**
	 * @param jp
	 * @param retVal
	 */
	public void afterReturning(JoinPoint jp, Object retVal)
	// capture the returned value or null is none was returned.
	{ String status = "completed Successfully";
	String returned = "";
	if (retVal != null) {
		System.out.println("Returned " + retVal.toString());
		returned = "Returned " + retVal.toString();
	} else
		returned = "Returned Null";
	Methodlog += returned  + status;
	}
	/**
	 * 
	 */
	public void afterthrow()
	{String status = "Did not complate ";
	Methodlog += status;
	}


}