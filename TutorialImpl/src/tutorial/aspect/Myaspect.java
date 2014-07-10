package tutorial.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

	/**
	 * Performed before method call execution
	 */
	public void dobefore(JoinPoint jp) {
		System.out
		.println("***************************Start of Before Method***************");
		startTime = System.currentTimeMillis();
		System.out.println("The method started at " + startTime);
		// System.out.println("" + jp.toString().getClass().getName());
		System.out.println("The method signature is " + jp.getSignature());
		System.out.println("The proxy Object is " + jp.getThis().getClass());
		System.out.println("location" + jp.getSourceLocation().toString());
		System.out.println("The target class is "
				+ jp.getTarget().getClass().getName());
		System.out.println("*************************** end of Before Method ***************"
				+ "\n");
	}

	/**
	 * Performed after method call execution
	 */
	public void doafter() {
		System.out
		.println("***************************Start of After Method***************");

		System.out.println("Iam after the method call");
		endTme = System.currentTimeMillis();
		System.out.println("The method ended at " + endTme);
		System.out.println("The method lasted " + (endTme - startTime)
				+ " Milliseconds");
		System.out
		.println("***************************End of After Method***************"
				+ "\n");
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out
		.println("***************************Start of Around Method***************");
		System.out.println("I do around every method");
		Object result = pjp.proceed();
		System.out
		.println("***************************End of Around Method***************"
				+ "\n");
		return result;
	}
	public void afterReturning(JoinPoint jp, Object retVal)
	{
		if (retVal != null)
		System.out.println("Returned " + retVal.toString());
		else System.out.println("Returned null");
	
	}
}
