package tutorialimpl;

import com.service.TutorialService;

public class ServiceImpl implements TutorialService{
	String name, matricNumber, program;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatricNumber() {
		return matricNumber;
	}

	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

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