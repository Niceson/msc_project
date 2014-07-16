package tutorial.policies;

public class Premise {
	//private  final String knowledge = "";
	
	private String bundle1, bundle2, variable;
	private Knowledge operator;
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
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		variable = variable;
	}
	public Knowledge getOperator() {
		return operator;
	}
	public void setOperator(Knowledge operator) {
		this.operator = operator;
	}
	
	
}
