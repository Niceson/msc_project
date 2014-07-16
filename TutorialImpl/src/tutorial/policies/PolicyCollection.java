package tutorial.policies;

import java.util.ArrayList;

public class PolicyCollection {
	ArrayList<Policy> myPolicies;

	public PolicyCollection()
	{
		myPolicies = new ArrayList<Policy>();
		
	}
	public void addPolicies(Policy pol)
	{
		myPolicies.add(pol);
	}
	/**
	 * Returns an array of policies that match a given method
	 * @param method
	 * @param params
	 * @return
	 */
	public ArrayList<Policy> getMyPolicies(String method, String params) {
		ArrayList<Policy> matches = new ArrayList<Policy>();
		for(int i = 0; i <myPolicies.size(); i++)
		{
			if(myPolicies.get(i)!= null){
				if(myPolicies.get(i).getMethod().equalsIgnoreCase(method)&&myPolicies.get(i).getParameters().equalsIgnoreCase(params))
				{
					
//					System.out.println("found policy " + myPolicies[i.getMethod());
//					System.out.println("method passes is " + method);
					matches.add(myPolicies.get(i));
				} else {
					System.out.println("No match");
				}
			} else {
				System.out.println("null policy");
			}
		}
		return matches;
	}

	public void printpols()
	{
		for(int i = 0; i<myPolicies.size();i++)
		{
			if(myPolicies.get(i)!= null)
			System.out.println(myPolicies.get(i).getMethod());
			else System.out.println("Its null too");
		}
	}
	
	public String createJSONString() {
		/*JSONObject jsonPolicyCollection = new JSONObject();
			JSONArray jsonMyPolicies = new JSONArray();
				for (Policy p : myPolicies) {
					JSONObject jsonPolicy = new JSONObject();
					jsonPolicy.put("name", p.getName());
					jsonPolicy.put("method", p.getMethod());
					jsonPolicy.put("parameter", p.getParameters());
					JSONArray jsonPremises = new JSONArray();
					for (Premise pre : p.getPremise()) {
						JSONObject jsonPremise = new JSONObject();
						jsonPremise.put("Bundle1", pre.getBundle1());
						jsonPremise.put("Bundle2", pre.getBundle2());
						jsonPremise.put("Variable", pre.getVariable());
						jsonPremise.put("operator", pre.getOperator());
						jsonPremises.put(jsonPremise);
					}
					JSONArray jsonConclusions = new JSONArray();
					for (Conclusion con : p.getConc()) {
						JSONObject jsonConclusion = new JSONObject();
						jsonConclusion.put("Bundle1", con.getBundle1());
						jsonConclusion.put("Bundle2", con.getBundle2());
						jsonConclusion.put("Variable", con.getVariable());
						jsonConclusion.put("operator", con.getOperator());
						jsonConclusion.put("state", con.getState());
						jsonConclusions.put(jsonConclusion);
					}
				}
				jsonPolicyCollection.put("myPolicies", jsonMyPolicies);
		return jsonPolicyCollection.toString();*/
		String jsonPolicyCollection = "{ \"myPolicies\": [ ";
			for (int i = 0; i < myPolicies.size(); i++) {
				Policy p = myPolicies.get(i);
				jsonPolicyCollection += "{ ";
				jsonPolicyCollection += "\"name\": \"" + p.getName() + "\", ";
				jsonPolicyCollection += "\"method\": \"" + p.getMethod() + "\", ";
				jsonPolicyCollection += "\"parameter\": \"" + p.getParameters() + "\", ";
				jsonPolicyCollection += "\"premise\": [ ";
				for (int j = 0; j < p.getPremise().size(); j++) {
					Premise pre = p.getPremise().get(j);
					jsonPolicyCollection += "{ ";
					jsonPolicyCollection += "\"Bundle1\": \"" + pre.getBundle1() + "\", ";
					jsonPolicyCollection += "\"Bundle2\": \"" + pre.getBundle2() + "\", ";
					jsonPolicyCollection += "\"Variable\": \"" + pre.getVariable() + "\", ";
					jsonPolicyCollection += "\"operator\": " + pre.getOperator().ordinal();
					if (j == p.getPremise().size() - 1) {
						jsonPolicyCollection += " } ";
					} else {
						jsonPolicyCollection += " }, ";
					}
				}
				jsonPolicyCollection += " ], ";
				jsonPolicyCollection += "\"conc\": [ ";
				for (int j = 0; j < p.getConc().size(); j++) {
					Conclusion con = p.getConc().get(j);
					jsonPolicyCollection += "{ ";
					jsonPolicyCollection += "\"Bundle1\": \"" + con.getBundle1() + "\", ";
					jsonPolicyCollection += "\"Bundle2\": \"" + con.getBundle2() + "\", ";
					jsonPolicyCollection += "\"Variable\": \"" + con.getVariable() + "\", ";
					jsonPolicyCollection += "\"operator\": " + con.getOperator().ordinal() + ", ";
					jsonPolicyCollection += "\"state\": " + con.getState().ordinal();
					if (j == p.getConc().size() - 1) {
						jsonPolicyCollection += " } ";
					} else {
						jsonPolicyCollection += " }, ";
					}
				}
				jsonPolicyCollection += " ] ";
				if (i == myPolicies.size() - 1) {
					jsonPolicyCollection += " } ";
				} else {
					jsonPolicyCollection += " }, ";
				}
			}
			jsonPolicyCollection += " ] }";
		return jsonPolicyCollection.toString();
	}
	
	public void readJSONString(String json) {
		//Read in the Json parsed file
		
	}
	
}
