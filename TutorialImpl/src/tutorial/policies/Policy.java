package tutorial.policies;

import java.util.ArrayList;
import java.util.Hashtable;

public class Policy {

	private String name;
	private ArrayList<Premise> premise;
	private ArrayList<Conclusion> conc;
	private int weight;
	Hashtable<String, Integer> m;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Policy()
	{
		premise = new ArrayList<Premise>();
		conc = new ArrayList<Conclusion>();
		m = new Hashtable<String, Integer>();
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

	public int getViolator(String client) {
		int violatedtimes=0;
		if(m.containsKey(client)){
			violatedtimes = m.get(client);
		}
		return violatedtimes;
	}

	public void setViolator(String client) {

		if(m.containsKey(client)){
			m.put(client, m.get(client)+1);
		}
		else{
			m.put(client, 1);
		}
	}


}