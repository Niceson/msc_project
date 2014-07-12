package clienttutorial.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ClientAspect {
	
	public void clientDobefore()
	{
		System.out.println("I am from before client methods");
	}
	
	public void clientDoafter()
	{
		System.out.println("I am after client methods");
	}
//	public void clientDoAround()
//	{
//		
//	}
	public void clientAfterReturning()
	{
		System.out.println("I am client after returning");
	}
}
