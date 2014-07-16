package tutorialimpl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.service.TutorialService;

public class Activator implements BundleActivator {
	private TutorialService service;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("server is started!");
	}
	
	/** (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

		// Bean properties
	    public TutorialService getService() {
	        return service;
	    }

	    public void setService(TutorialService service) {
	    	System.out.println("i just set service" + service);
	        this.service = service;
	    }

}
