package tutorialimpl;

import com.service.TutorialService;

public class ServiceImpl implements TutorialService{

	@Override
	public void sayHello(String name) {
		System.out.println( "Hello my World!" + name );	
	}
}