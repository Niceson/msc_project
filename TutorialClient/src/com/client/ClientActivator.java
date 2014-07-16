package com.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.service.TutorialService;

	public class ClientActivator implements BundleActivator {
		private ServiceTracker track;

		public void start(BundleContext context) throws Exception {
			System.out.println("Client Started");
			track = new ServiceTracker (context, TutorialService.class.getName(), null)
			{
			@Override
				public Object addingService(ServiceReference reference){
					Object svc = super.addingService(reference);
					if (svc instanceof TutorialService){
						invokeService((TutorialService) svc);
					}
					return svc;
				}
			};
			track.open();
			}
			
		

		public void stop(BundleContext context) throws Exception {
			track.close();
			
		}
		void invokeService(TutorialService svc){
			int input = 20;
			System.out.println("Invoking Service with input " + input);
			System.out.println(" Result: " + svc.sum(input));
		}

}
