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
 * Returns an array of policies that match a given method either specified in the premise or in the conclusion
 * @param method
 * @param params
 * @param classname
 * @return
 */
	public ArrayList<Policy> getMyPolicies(String method, String params, String classname) {
		ArrayList<Policy> matches = new ArrayList<Policy>();
		
		for(int i = 0; i <myPolicies.size(); i++)
		{
			boolean found = false;
			if(myPolicies.get(i)!= null){
				ArrayList<Premise> prem = myPolicies.get(i).getPremise();	
				for (int p = 0; p<prem.size();p++)
				{
					if(prem.get(p).getMethod().equalsIgnoreCase(method) && prem.get(p).getParameter().equalsIgnoreCase(params)
							&& prem.get(p).getBundle1().equalsIgnoreCase(classname)&& !found)
					{
						matches.add(myPolicies.get(i));
						found=true;	
					}
				}
				if(!found)
				{
					ArrayList<Conclusion> conc =  myPolicies.get(i).getConc();
							for (int c = 0; c<conc.size();c++)
							{
								if(conc.get(c).getMethod().equalsIgnoreCase(method) && conc.get(c).getParameter().equalsIgnoreCase(params)
										&& conc.get(c).getBundle1().equalsIgnoreCase(classname)&& !found)
						
								{
									matches.add(myPolicies.get(i));
									found=true;	
								}
							}
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
		//	if(myPolicies.get(i)!= null)
		//	System.out.println(myPolicies.get(i).getMethod());
		//	else System.out.println("Its null too");
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
				//jsonPolicyCollection += "\"parameter\": \"" + p.getParameters() + "\", ";
				jsonPolicyCollection += "\"premise\": [ ";
				for (int j = 0; j < p.getPremise().size(); j++) {
					Premise pre = p.getPremise().get(j);
					jsonPolicyCollection += "{ ";
					jsonPolicyCollection += "\"Bundle1\": \"" + pre.getBundle1() + "\", ";
					jsonPolicyCollection += "\"Method\": \"" + pre.getMethod() + "\", ";
					jsonPolicyCollection += "\"Parameter\": \"" + pre.getParameter() + "\", ";
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
					jsonPolicyCollection += "\"Method\": \"" + con.getMethod() + "\", ";
					jsonPolicyCollection += "\"Parameter\": \"" + con.getParameter() + "\", ";
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
	
}
