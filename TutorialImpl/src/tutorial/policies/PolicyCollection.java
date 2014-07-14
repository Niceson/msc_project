package tutorial.policies;

public class PolicyCollection {
	private Policy[] myPolicies,matches;
	int start = 0;
	
	public PolicyCollection()
	{
		myPolicies = new Policy[7];
	}
	public void addPolicies(Policy pol)
	{
		if(start<myPolicies.length)
		{
			myPolicies[start] = pol;
		}
	}
	/**
	 * Returns an array of policies that match a given method
	 * @param method
	 * @param params
	 * @return
	 */
	public Policy[] getMyPolicies(String method, String params) {
		matches = new Policy[7];
		int position = 0;
		for(int i = 0; i <myPolicies.length; i++)
		{
			if(myPolicies[i].getMethod().equalsIgnoreCase(method)&& myPolicies[i].getParameters().equalsIgnoreCase(params) )
			{
				matches[position]= myPolicies[i];
				position++;
			}
		}
		return matches;
	}

}
