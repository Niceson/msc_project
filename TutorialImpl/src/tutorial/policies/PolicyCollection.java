package tutorial.policies;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
					//System.out.println("" + classname + "\n"+ method + "\n"+ params);

					if(prem.get(p).getMethod().equalsIgnoreCase(method) && prem.get(p).getParameter().equalsIgnoreCase(params)
							&& prem.get(p).getBundle1().equalsIgnoreCase(classname)&& !found)
					{
						matches.add(myPolicies.get(i));
						found=true;	
						//	System.out.println("Policy" + myPolicies.get(i).getName() + " found! and added to array");
						break;
						//					}
						//					else{
						//						
						//					}
					}

					if(!found)
					{	//System.out.println("No matching policy"+ myPolicies.get(i).getName() + " found in the premise");
						ArrayList<Conclusion> conc =  myPolicies.get(i).getConc();
						for (int c = 0; c<conc.size();c++)
						{
							//						System.out.println("My conclusion has Bundle 1 as " + conc.get(c).getBundle1());
							//						System.out.println("class sent is " + classname );
							if(conc.get(c).getMethod().equalsIgnoreCase(method) && conc.get(c).getParameter().equalsIgnoreCase(params)
									&& conc.get(c).getBundle1().equalsIgnoreCase(classname)&& !found)
							{
								//System.out.println("policy" + myPolicies.get(i).getName() +" found matches a conclusion");
								matches.add(myPolicies.get(i));
								found=true;	
								break;
							}
							//						else{
							//						//	System.out.println("policy"+ myPolicies.get(i).getName() +" not matching any conclusions");
							//			    	}
						}
					}
				}
			}
			else {
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

	public void readJSONString(String input){
		//Find, split,search. and repeat. 

		String pattern="\\{.*\\}";
	//	String pattern="\\[.*\\],";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		System.out.println(input);
		while (m.find()) {
			System.out.print("Start index: " + m.start());
		      System.out.print(" End index: " + m.end() + " ");
		      System.out.println(m.group());
//			System.out.println("match found");
//			System.out.println(m.group(0));
//			// System.out.println(m.group(1));
//			for (int i=0; i<m.groupCount(); i++)
//			{
//				System.out.println("I am printing matches");
//				System.out.println(m.group(i));
//			}
		}
//			String [] inputrecord = input.split("[ ,{}:.]+");
//			if (inputrecord.length < 4 )
//			{
//				System.out.print("Missing data");
//			}
//			else 
//			{
//				for (int i=0; i<inputrecord.length;i++)
//				{
//					System.out.println("" + inputrecord[i]);
//				}
//			}	
//			for(int i=0; i<input.length(); i ++)
//			{
//
//			}

	}

}

