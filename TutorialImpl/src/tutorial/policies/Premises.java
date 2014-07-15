package tutorial.policies;

public class Premises {
	//private  final String knowledge = "";
	private String method,parameters;
	private boolean knowledge,rate;
	
	public boolean isRate() {
		return rate;
	}
	public void setRate(boolean rate) {
		this.rate = rate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public boolean isKnowledge() {
		return knowledge;
	}
	public void setKnowledge(boolean knowledge) {
		this.knowledge = knowledge;
	}
	@Override
	public String toString() {
		return "Premises [knowledge=" + knowledge + ", method=" + method
				+ ", parameters=" + parameters + ", rate=" + rate + "]";
	}

}
