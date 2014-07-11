package tutorial.aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.patterns.ThisOrTargetPointcut;

/**
 * My Aspect class
 * 
 * @author Nice
 * 
 */
public class Myaspect {
	private Long startTime, endTme;
	private String Finallog;
	private String Methodlog;
	/**
	 * Performed before method call execution
	 */
	public void dobefore(JoinPoint jp) {
		Methodlog="***************** Bundle History*********************" +"\n";
		System.out
		.println("***************************Start of Before Method***************");
		startTime = System.currentTimeMillis();

		System.out.println("The method started at " + startTime);
		String time = "The method started at: " + startTime;
		// System.out.println("" + jp.toString().getClass().getName());
		String signature= " The method signature is: "+ jp.getSignature();
		System.out.println("The method signature is " + jp.getSignature());
		//System.out.println("The proxy Object is " + jp.getThis().getClass());
		//System.out.println("location" + jp.getSourceLocation().toString());
		System.out.println("The target class is "
				+ jp.getTarget().getClass().getName());
		String targetClass = "The target class is: "+ jp.getTarget().getClass().getName();

		System.out.println("*************************** end of Before Method ***************"
				+ "\n");

		Methodlog = Methodlog + String.format(("%4s %8s %10s %n") ,signature + "\n",time + "\n",targetClass + "\n");
	}

	/**
	 * Performed after method call execution
	 */
	public void doafter() {
		//FileWriter writelog = null;
		System.out
		.println("***************************Start of After Method***************");
		endTme = System.currentTimeMillis();
		String ended = "Method ended at: " + endTme;
		System.out.println("The method ended at " + endTme);
		System.out.println("The method lasted " + (endTme - startTime)
				+ " Milliseconds");
		String duration = "The method lasted: " + (endTme - startTime)
				+ " Milliseconds";

		Methodlog += String.format(("%4s %4s %n"),ended + "\n",duration + "\n");
		Finallog += Methodlog;
		System.err.println(Finallog);
		writetofile();
		System.out
		.println("***************************End of After Method***************"
				+ "\n");
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("***************************Start of Around Method***************");
		System.out.println("calling class is " + pjp.getThis());
		Object[] args=pjp.getArgs();
		if(args.length>0){
			System.out.print("Arguments passed: ");
			String parameters = "";
			for (int i = 0; i < args.length; i++) {
				System.out.print("Arg"+(i+1)+":"+args[i] + "\n");
				parameters = "Arguments passed are:"+ args[i];
			}
			Methodlog += parameters+ "\n";
		}

		Object result = pjp.proceed();
		System.out.println("***************************End of Around Method***************"+ "\n");
		return result;
	}


	public void afterReturning(JoinPoint jp, Object retVal)
	{String returned = "";
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
	//	PrintWriter writer = new PrintWriter("C:\\BluPrintProject\\TutorialImpl\\log.txt", "UTF-8");

		PrintWriter writer = new PrintWriter("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt", "UTF-8");
		writer.println(Finallog);
		writer.close();

//		BufferedWriter writelog = new BufferedWriter(new FileWriter("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt"));
//		//writelog = new FileWriter("log.txt"); 
//		writelog.write(Finallog);
	}
	catch (IOException p)
	{
		System.out.print("Not found!");
	}
}
}
