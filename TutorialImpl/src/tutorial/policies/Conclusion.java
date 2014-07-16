package tutorial.policies;

public class Conclusion {
	
	private String bundle1, bundle2;
	private Knowledge operator;
	private Status state;
	private String variable;
	
	public String getBundle1() {
		return bundle1;
	}
	
	public void setBundle1(String bundle1) {
		bundle1 = bundle1;
	}
	
	public String getBundle2() {
		return bundle2;
	}
	
	public void setBundle2(String bundle2) {
		bundle2 = bundle2;
	}
	
	public Knowledge getOperator() {
		return operator;
	}
	
	public void setOperator(Knowledge operator) {
		this.operator = operator;
	}
	
	public Status getState() {
		return state;
	}
	
	public void setState(Status state) {
		this.state = state;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
}
