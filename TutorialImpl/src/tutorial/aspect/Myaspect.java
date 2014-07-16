package tutorial.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import tutorial.policies.PolicyAnalysis;

/**
 * My Aspect class
 * 
 * @author Nice
 * 
 */
public class Myaspect {
	private Long startTime, endTme;
	private String finallog;
	private String methodlog = "";
	private PolicyAnalysis myAnalysis = new PolicyAnalysis();

	/**
	 * Performed before method call execution
	 * 
	 * Before method execution. load policies say from a file one after the other :P
	 * 
	 */
	public void dobefore(JoinPoint jp) {
		// generate the stack trace to get the calling class
//		StackTraceElement[] stackTraceElements = Thread.currentThread()
//				.getStackTrace();
//		for (int i = 0; i < stackTraceElements.length; i++) {
//
//			if (stackTraceElements[i].getClassName().equals("sun.reflect.NativeMethodAccessorImpl")
//					&& !(stackTraceElements[i - 1].getClassName().equals(jp.getThis().getClass().getName()))) {
//				if (!(stackTraceElements[i - 1].getClassName().equals(this.getClass().getName()))
//						&& !(stackTraceElements[i - 1].getClassName().equals("sun.reflect.NativeMethodAccessorImpl"))) {
//					System.out.println("The proxy is " + i
//							+ stackTraceElements[i].getClassName());
//					
//				System.out.println("on stack " + (i - 1)+ stackTraceElements[i - 1].getClassName());
////				ClassLoader classLoader = stackTraceElements[i - 1].getClass().getClassLoader();
////				System.out.println(classLoader);
////				
////				BundleReference bref = (BundleReference) classLoader;
////				System.out.println(bref);
//				
////				Class c = null;
////				try {
////					c = Class.forName(stackTraceElements[i - 1].getClassName());
////					Method[] methods = c.getMethods();
////					try {
////						final Method amethod = c.getDeclaredMethod("getTest", null);
////						System.out.println(amethod.isAccessible());
////					} catch (NoSuchMethodException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					} catch (SecurityException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////							//aclass.getDeclaredMethod(amethodname, parameterTypes);
////				} catch (ClassNotFoundException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//					
//				// Try to get the client Object to access the client
//				
//					String className = stackTraceElements[i - 1].getClassName();
//					methodlog += "Name:" + className + ",";	
//				}
//			}
//		}
		startTime = System.currentTimeMillis();// captures the start time of the method call
		String time = "started:" + startTime;
		// System.out.println("" + jp.toString().getClass().getName());
		String signature = "methodSignature:" + jp.getSignature();
		String name = "methodName"+ jp.getSignature().getName();
		// System.out.println("The proxy Object is " + jp.getThis().getClass());
		String targetClass = "targetClass:"
				+ jp.getTarget().getClass().getName();
		
		
		methodlog = methodlog + name+ "," +  signature + ","+  time	+ ","+ targetClass +",";
	}

	/**
	 * Performed after method call execution
	 */
	public void doafter() {

		endTme = System.currentTimeMillis();
		String ended = "MethodEnded " + endTme;
		String duration = "Duration " + (endTme - startTime)
				+ " Milliseconds";
		methodlog += ended + duration;
		finallog += methodlog;
		myAnalysis.writetoFile(finallog);// calls method to write to file the log of all methods.
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
		
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		String className="";
		for (int i = 0; i < stackTraceElements.length; i++) {

			if (stackTraceElements[i].getClassName().equals("sun.reflect.NativeMethodAccessorImpl")
					&& !(stackTraceElements[i - 1].getClassName().equals(pjp.getThis().getClass().getName()))) {
				if (!(stackTraceElements[i - 1].getClassName().equals(this.getClass().getName()))
						&& !(stackTraceElements[i - 1].getClassName().equals("sun.reflect.NativeMethodAccessorImpl"))) {
				className = stackTraceElements[i - 1].getClassName();
				methodlog += "Name:" + className + ",";	
			}
			}
		}
		Object result= null;
		Object[] args = pjp.getArgs();
		String parameters = "";
		if (args.length > 0) {
			// captures method arguments
			for (int i = 0; i < args.length; i++) {
				parameters += args[i];
			}
			methodlog += parameters;// appends the method log
		}
		String passedMethod = pjp.getSignature().getName();
		
	System.out.println("Method passed " +  pjp.getSignature().getName());
		myAnalysis.readPolicy();// read in the policies before the method executes.
		//returns the number of violations a method has committed
		int deciding = myAnalysis.Analyse(passedMethod, parameters,className);
		
		if (deciding==0) {// no violation proceed with method execution
			result = pjp.proceed();	
		}
			else if(deciding < 10) 	{//Number of violations are low
				//increase tendency by a lower magnitude
				
			}
				
		else {// Unacceptable level; throw exception to stop method execution
			
			throw new Throwable();
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
	//	System.out.println("Returned " + retVal.toString());
		returned = "Returned " + retVal.toString();
	} else
		returned = "Returned Null";
	methodlog += returned  + status;
	}
	/**
	 * 
	 */
	public void afterthrow()
	{String status = "Did not complate ";
	methodlog += status;
	}


}