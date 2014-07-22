package com.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.service.TutorialService;

	public class ClientActivator implements BundleActivator {
		ServiceReference refers;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("okay client");
		refers = context.getServiceReference(TutorialService.class.getName());
		TutorialService gotservice = (TutorialService) context.getService(refers);
		if(gotservice!= null && gotservice instanceof TutorialService)
		{
			invokeService((TutorialService) gotservice);
		}
		System.out.println("This service is not an instance of your interface" + gotservice);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		//track.close();
				context.ungetService(refers);
		
	}


	void invokeService(TutorialService svc){
		int input = 20;
		svc.sayHello("Nice");
		System.out.println("Invoking Service with input " + input);
		System.out.println(" Result: " + svc.sum(input));
	}
		
//		ServiceReference refers;
//
//		public void start(BundleContext context) throws Exception {
//			System.out.println("Client Started");
//			track = new ServiceTracker (context, TutorialService.class.getName(), null)
//			{
//			@Override
//				public Object addingService(ServiceReference reference){
//					Object svc = super.addingService(reference);
//					if (svc instanceof TutorialService){
//						invokeService((TutorialService) svc);
//					}
//					return svc;
//				}
//			};
//			track.open();
//			}
//			
//		
//
//		public void stop(BundleContext context) throws Exception {
//			track.close();
//			
//		}
//		void invokeService(TutorialService svc){
//			int input = 20;
//			System.out.println("Invoking Service with input " + input);
//			System.out.println(" Result: " + svc.sum(input));
//		}

}
