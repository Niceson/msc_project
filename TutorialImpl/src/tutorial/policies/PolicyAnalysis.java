package tutorial.policies;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PolicyAnalysis {
	private PolicyCollection collect;

	public PolicyAnalysis()
	{
		collect = new PolicyCollection();
		Policy pol = new Policy();
		Conclusion c = new Conclusion();
		Premise p = new Premise();

		p.setBundle1("com.client.Client");
		p.setOperator(Knowledge.KNOWS);
		p.setMethod("sayHello");
		p.setParameters("Nice");

		c.setBundle1("com.client.Client");
		c.setOperator(Knowledge.KNOWS);
		c.setState(Status.ALWAYS);
		c.setMethod("sayHello");
		c.setParameter("Nice");
		pol.addConc(c);

		pol.setName("Policy1");
		pol.setWeight(5);
		pol.addPremise(p);
		collect.addPolicies(pol);

		Policy pol2 = new Policy();
		Conclusion c2 = new Conclusion();
		Premise p2 = new Premise();

		p2.setBundle1("com.client.Client");
		p2.setOperator(Knowledge.KNOWS);
		p2.setMethod("sum");
		p2.setParameters("2");

		c2.setBundle1("com.client.Client");
		c2.setOperator(Knowledge.NOT_KNOWS);
		c2.setState(Status.ALWAYS);
		c2.setMethod("sayHello");
		c2.setParameter("Nice");


		pol2.setName("Policy2");
		pol2.setWeight(2);
		pol2.addPremise(p2);
		pol2.addConc(c2);

		collect.addPolicies(pol2);
		policytoFile();
		readPolicy();// method that reads policies from file
	
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
		String violation = "";
		for (int i = 0; i < matchPolicies.size(); i++) {
			// calls helper method to check if a policy has been violated and how many times it has been violated
			if(matchPolicies.get(i)!=null){
				System.out.println("verifying policy " + matchPolicies.get(i).getName());
				if(verifypremise(matchPolicies.get(i)))// return true if all premises in this policy are true
				{
					System.out.println(" policy " + matchPolicies.get(i).getName() + "has been verified");
					System.out.println("policy " + matchPolicies.get(i).getName() + "is being checked for violation");
					if(checkViolation(matchPolicies.get(i),classname,method,params))
					{ 
						policiesviolated++;// increment the number of policies violated
						violation += matchPolicies.get(i).getName() + classname + method + params +"was violated";
						System.out.println(violation);
						writeLogtoFile(violation);
						matchPolicies.get(i).setViolator(classname);
						System.out.println(matchPolicies.get(i).getViolator(classname));

						//collect.infotowrite(matchPolicies.get(i));
						System.out.println("" + matchPolicies.get(i).getName() + "Has been violated");
					}

					else{
						//The policy has not been violated
						System.out.println("" + matchPolicies.get(i).getName() + "Has not been violated");
					}
				}
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
		boolean settrue = false;
		//check premise	
		ArrayList<Premise> prem = poly.getPremise(); 
		for(int i=0;i<prem.size();i++)
		{// if it already knows. 
			if(prem.get(i).isIstrue()){
				//System.out.println("" + prem.get(i).getBundle1() + prem.get(i).getMethod() + prem.get(i).getParameter() + prem.get(i).getOperator().name() + "has been met before");
				settrue = true;
				//do nothing
			}else
			{
				System.out.println("" + prem.get(i).getBundle1() + prem.get(i).getMethod() + prem.get(i).getParameter() + prem.get(i).getOperator().name() + "has been NOT been met before");
				prem.get(i).setIstrue(true);
				//System.out.println(prem.get(i).isIstrue());
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
	 * checks if a policy has been violated or not
	 * @param pol
	 * @param client
	 * @param method
	 * @param param
	 * @return
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
	/**
	 * Reads policies from a policies file
	 */

	public void readPolicy()
	{
		FileReader readpolicy = null;
		try {
			readpolicy = new FileReader("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\policies.txt");
			Scanner policyfile = new Scanner(readpolicy);
			String input = "";

			while (policyfile.hasNextLine()) {
				input += policyfile.nextLine();
			}
			collect.readJSONString(input);//call a helper method to read the JSON string.
		} catch (IOException e) {
			System.out.println("policy file not found");
		} finally {
			// closes the opened files
			try {

				readpolicy.close();
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

	public void writeLogtoFile(String log)
	{
		FileWriter fop = null;
		File file;
		try {
			
			file = new File("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			fop = new FileWriter(file, true);
		fop.append(log);
			
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
//			PrintWriter writer = new PrintWriter(
//					"C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\log.txt",
//					"UTF-8");
//			writer.println(log);
			
			finally {
				try {
					if (fop != null) {
						fop.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
	}
	/**
	 * Writes policies to file.
	 */
	public void policytoFile()
	{
			FileWriter fop = null;
			File file;
			try {
				file = new File("C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\policies.txt");
				
				if (!file.exists()) {
					file.createNewFile();
				}
				fop = new FileWriter(file, true);
			fop.append(collect.createJSONString());
			}

//			PrintWriter writer = new PrintWriter(
//					"C:\\Users\\Nice\\Documents\\GitHub\\msc_project\\TutorialImpl\\policies.txt",
//					"UTF-8");
//			writer.println(collect.createJSONString());
////			writer.close();
		 catch (IOException p) {
			System.out.print("Not found!");
		}
//	}
		finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
