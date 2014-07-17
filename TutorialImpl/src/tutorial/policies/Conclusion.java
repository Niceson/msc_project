package tutorial.policies;

public class Conclusion {
	
	private String bundle1;
	private Knowledge operator;
	private Status state;
	private String method;
	String parameter;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getBundle1() {
		return bundle1;
	}
	
	public void setBundle1(String bundle1) {
		this.bundle1 = bundle1;
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

}
