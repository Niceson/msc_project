package tutorial.policies;

public class Premise {
	//private  final String knowledge = "";
	private String method;
	private String parameter;
	private String bundle1;
	private Knowledge operator;
	private boolean istrue = false;
	
	public boolean isIstrue() {
		return istrue;
	}
	public void setIstrue(boolean istrue) {
		this.istrue = istrue;
	}
	public String getBundle1() {
		return bundle1;
	}
	public void setBundle1(String bundle1) {
		bundle1 = bundle1;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameters(String parameters) {
		this.parameter = parameters;
	}
	public Knowledge getOperator() {
		return operator;
	}
	public void setOperator(Knowledge operator) {
		this.operator = operator;
	}

}
