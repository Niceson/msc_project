package tutorial.policies;

public class Premise {
	//private  final String knowledge = "";
	
	private String Bundle1, Bundle2, Variable;
	private knowledge operator;
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
	public String getVariable() {
		return Variable;
	}
	public void setVariable(String variable) {
		Variable = variable;
	}
	public knowledge getOperator() {
		return operator;
	}
	public void setOperator(knowledge operator) {
		this.operator = operator;
	}
	
	
}
