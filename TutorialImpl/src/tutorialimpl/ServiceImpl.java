package tutorialimpl;

import com.service.TutorialService;

public class ServiceImpl implements TutorialService{
	

	@Override
	public void sayHello(String name) {
		System.out.println( "Hello my World!" + name );	
		
	}

	@Override
	public int sum(int add) {
		int total = add*2;
		return total;
	}
}