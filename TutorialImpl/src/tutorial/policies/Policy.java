package tutorial.policies;

import java.util.ArrayList;

public class Policy {
	private String name;
	private String method;
	private String parameters;
	
	ArrayList<Premise> premise;
	ArrayList<Conclusion> conc;
	
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

	public Policy()
	{
		premise = new ArrayList<Premise>();
		conc = new ArrayList<Conclusion>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Premise> getPremise() {
		return premise;
	}

	public void setPremise(ArrayList<Premise> premise) {
		this.premise = premise;
	}
	
	public void addPremise(Premise premise) {
		this.premise.add(premise);
	}

	public ArrayList<Conclusion> getConc() {
		return conc;
	}

	public void setConc(ArrayList<Conclusion> conc) {
		this.conc = conc;
	}
	
	public void addConc(Conclusion conc) {
		this.conc.add(conc);
	}
	
	
}