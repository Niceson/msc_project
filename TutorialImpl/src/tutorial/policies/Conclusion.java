package tutorial.policies;

public class Conclusion {
	
	private String Bundle1, Bundle2;
	private knowledge operator;
	private Status state;
	private String variable;
	
	public String getBundle1() {
		return Bundle1;
	}
	
	public void setBundle1(String bundle1) {
		Bundle1 = bundle1;
	}
	
	public String getBundle2() {
		return Bundle2;
	}
	
	public void setBundle2(String bundle2) {
		Bundle2 = bundle2;
	}
	
	public knowledge getOperator() {
		return operator;
	}
	
	public void setOperator(knowledge operator) {
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
