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
	private Long startTime;
	private Long endTme;
	private int tendency = 0;
	private String finallog;
	private String methodlog = "";
	private PolicyAnalysis myAnalysis = new PolicyAnalysis();;
	Logging logs= new Logging();
	
	// Declare map for multiple values e.g HashMap<String,ArrayList<String>> map;
	
	/**
	 * Performed before method call execution
	 * 
	 * Before method execution. load policies say from a file one after the other :P
	 * 
	 */
	public void dobefore(JoinPoint jp) {
		
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
		methodlog += ended + duration + tendency;
		finallog += methodlog;
		
		myAnalysis.writeLogtoFile(finallog);// calls method to write to file the log of all methods.
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
		int highmagnitude = 5;
		int lowmagnitude = 3; 
		String classname="";
		Object result= null;
		//gets the calling class name
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		for (int i = 0; i < stackTraceElements.length; i++) {
			if (stackTraceElements[i].getClassName().equals("sun.reflect.NativeMethodAccessorImpl")
					&& !(stackTraceElements[i - 1].getClassName().equals(pjp.getThis().getClass().getName()))) {
				if (!(stackTraceElements[i - 1].getClassName().equals(this.getClass().getName()))
						&& !(stackTraceElements[i - 1].getClassName().equals("sun.reflect.NativeMethodAccessorImpl"))) {
					classname = stackTraceElements[i - 1].getClassName();//calling class name assigned to variable class name
					methodlog += "Name:" + classname + ",";	
				}
			}
		}
		//gets passed arguments
		Object[] args = pjp.getArgs();
		String parameters = "";
		if (args.length > 0) {
			// captures method arguments
			for (int i = 0; i < args.length; i++) {
				parameters += args[i];
			}
			methodlog += parameters;// appends the method log
		}
		//assign the signature of the method called
		String passedMethod = pjp.getSignature().getName();

		//System.out.println("Method passed " +  pjp.getSignature().getName());
		// Not working for now.....>>>	myAnalysis.readPolicy();// read in the policies before the method executes.
		//returns the number of violations a method has committed

		int deciding = myAnalysis.Analyse(passedMethod, parameters,classname);

		if (deciding==0) {// no violation proceed with method execution
			result = pjp.proceed();	
			System.out.println("This policy has not been violated, the method can proceed");
		}
		else if(deciding < 10) 	{//Number of violations are low
			//increase tendency by a lower magnitude
			tendency += lowmagnitude;
			System.out.println("This method has violated few policies, it can proceed but with caution ");
		}

		else {// Unacceptable level; throw exception to stop method execution
			System.out.println("This method needs to be blocked, it has violated a lot of policies");
			tendency+=highmagnitude;
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
	{String status = "method did not complete ";
	methodlog += status;
	}

}