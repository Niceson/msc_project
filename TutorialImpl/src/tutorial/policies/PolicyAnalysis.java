package tutorial.policies;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PolicyAnalysis {
	private PolicyCollection collect;
	private int violationTimes,highmagnitude = 5, lowmagnitude = 3, tendency;

	public PolicyAnalysis()
	{
		collect = new PolicyCollection();
		Policy pol = new Policy();
		Conclusion c = new Conclusion();
		Premise p = new Premise();

		p.setBundle1("Client");
		p.setOperator(Knowledge.KNOWS);
		p.setMethod("sayHello");
		p.setParameters("Nice");

		c.setBundle1("Client");
		c.setOperator(Knowledge.KNOWS);
		c.setState(Status.ALWAYS);
		c.setMethod("sayHello");
		c.setParameter("Nice");
		pol.addConc(c);

		pol.setName("Policy1");
		pol.addPremise(p);
		collect.addPolicies(pol);

		Policy pol2 = new Policy();
		Conclusion c2 = new Conclusion();
		Premise p2 = new Premise();

		p2.setBundle1("Client");
		p2.setOperator(Knowledge.KNOWS);
		p2.setMethod("sum");
		p2.setParameters("2");

		c2.setBundle1("Client");
		c2.setOperator(Knowledge.NOT_KNOWS);
		c2.setState(Status.ALWAYS);
		c2.setMethod("sayHello");
		c2.setParameter("Nice");


		pol2.setName("Policy2");
		pol2.addPremise(p2);
		pol2.addConc(c2);

		collect.addPolicies(pol2);

		policytoFile();
	}

	/**
	 * Returns applicable policies related to a particular method and checks if
	 * any has been violated if any has, a policies a method has been violated then, it calls the method to verify the
	 * history log.
	 * @param method
	 */
	public int Analyse(String method, String params, String classname) {
		ArrayList<Policy> matchPolicies = collect.getMyPolicies(method,params,classname);
		int policiesviolated = 0;
		for (int i = 0; i < matchPolicies.size(); i++) {
			// calls helper method to check if a policy has been violated and how many times it has been violated
			if(matchPolicies.get(i)!=null){
				if(verifypremise(matchPolicies.get(i)))// return true if all premises in this policy are true
				{
					if(checkViolation(matchPolicies.get(i),classname,method,params))
					{
						policiesviolated++;
						System.out.println("" + matchPolicies.get(i).getName() + "Has been violated");
					}
					//System.out.println("Policy " + matchPolicies.get(i).getName() + " has been violated many times " + violationTimes);
					
					
				}// could be added that if one policy has been violated many times but no other policy has been violated.

				
					//The policy has not been violated
		 else{
				System.out.println("No matching policies found");
			}
			}
		}
		return policiesviolated;
	}
	/**
	 * Verifies from the log file (log.txt) and sets how many times
	 * a certain policy related to a method has been violated by
	 * a given class in the past
	 * This will help us determine the security measures to be taken
	 * depending on the previous interactions
	 * @param poly the policy that has been violated
	 * @param method the method to which this policy is applied
	 * @return
	 */
	public boolean verifypremise(Policy poly) {
		violationTimes=0;//will keep how many times a particular policy has been violated
		boolean settrue = false;
		//check premise	
		ArrayList<Premise> prem = poly.getPremise(); 
		for(int i=0;i<prem.size();i++)
		{
			if(prem.get(i).isIstrue()){
				settrue = true;
				//do nothing
			}else
			{
				prem.get(i).setIstrue(false);
				return false;
			}
		}
		//		FileReader readLog = null;
		//		try {
		//			try {
		//				readLog = new FileReader("log.txt");
		//				Scanner LogsFile = new Scanner(readLog);
		//				while (LogsFile.hasNextLine()) {
		//					String logRecord = LogsFile.nextLine();
		//					System.out.println(logRecord);
		//					//Check if policy has been violated and if yes increment violationTimes.
		//				}
		//			} finally {
		//				// closes the opened files
		//				if (readLog != null)
		//					readLog.close();
		//			}
		//		} catch (IOException e) {
		//			System.out.println("log file not found");
		//		}
		return settrue;
	}
	/**
	 * Reads policies before the method execution.
	 * @param log
	 */

	public boolean checkViolation(Policy pol, String client, String method, String param)
	{
		boolean violated = false;
		ArrayList<Conclusion> conc = pol.getConc();
		for(int i = 0; i< conc.size();i++)
		{
			if(conc.get(i).getBundle1().equalsIgnoreCase(client) && conc.get(i).getMethod().equalsIgnoreCase(method) 
					&& conc.get(i).getParameter().equalsIgnoreCase(param))
			{	
         if(conc.get(i).getOperator().name().equalsIgnoreCase("KNOWS"))
				violated = true;			
			}
		}
		return violated;
	}


	public void readPolicy()
	{
		FileReader readPolicy = null;
		try {
			readPolicy = new FileReader("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\policies.txt");
			Scanner PolicyFile = new Scanner(readPolicy);
			String input = "";
			while (PolicyFile.hasNextLine()) {
				input += PolicyFile.nextLine();
			}  
			this.readJSONString(input);//call a helper method to read the JSON string.
		} catch (IOException e) {
			System.out.println("policy file not found");
		} finally {
			// closes the opened files
			try {
				readPolicy.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Writes the log to file after method execution whether it has successfully completed or not
	 * @param log
	 */

	public void writetoFile(String log)
	{
		try {

			PrintWriter writer = new PrintWriter(
					"C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt",
					"UTF-8");
			writer.println(log);
			writer.close();
		} catch (IOException p) {
			System.out.print("Not found!");
		}
	}

	public void policytoFile()
	{
		try {

			PrintWriter writer = new PrintWriter(
					"C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\policies.txt",
					"UTF-8");
			writer.println(collect.createJSONString());
			writer.close();
		} catch (IOException p) {
			System.out.print("Not found!");
		}
	}



	public void readJSONString(String input){
		//Try looking at find to be used in string
		collect = new PolicyCollection();
		new Policy();
		new Conclusion();

		String [] singleClass = input.split("[ ,{}:.]+");
		if (singleClass.length < 4 )
		{
			System.out.print("Missing data");
		}
		else 
		{

		}	

		for(int i=0; i<input.length(); i ++)
		{

		}

		//	collect.addPolicies(pol2);


	}
}
