package tutorial.policies;

public class Policy {
	private String name;
	private Premises premise;
	Conclusions conclusion;
	
	public Policy()
	{
		premise = new Premises();
		conclusion = new Conclusions();
	}
	
	public String getName() {
		return name;
	}
	
	public Premises getPremise() {
		return premise;
	}

	public void setPremise(Premises premise) {
		this.premise = premise;
	}

	public Conclusions getConclusion() {
		return conclusion;
	}

	public void setConclusion(Conclusions conclusion) {
		this.conclusion = conclusion;
	}

	public void setName(String name) {
		this.name = name;
	}
}
