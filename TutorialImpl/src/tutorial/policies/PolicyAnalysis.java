package tutorial.policies;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PolicyAnalysis {
	PolicyCollection collect = new PolicyCollection();
	private int violationTimes, highmagnitude = 5, lowmagnitude = 3, tendency;
	public PolicyAnalysis()
	{
	}
	/**
	 * Returns applicable policies related to a particular method and checks if
	 * any has been violated if any has, it calls the method to verify the
	 * history log.
	 * @param method
	 */
	public boolean Analyse(String method, String params) {
		Policy[] matchPolicies = collect.getMyPolicies(method,params);
		int policiesViolated = 0;
		boolean violated = false;
		for (int i = 0; i < matchPolicies.length; i++) {
			// calls helper method to check if a policy has been violated and how many times it has been violated

			if(verifyViolation(matchPolicies[i])&& violationTimes>10);
			{//could be updated to say; if it has been violated many times or is not the only policy violated.
				//It is unacceptable, tell aspect to terminate this method execution and append the status to file
				System.out.println("Policy " + matchPolicies[i].getName() + " has been violated many times " + violationTimes);
				policiesViolated++;
				violated= true;
			}// could be added that if one policy has been violated many times but no other policy has been violated.
			else if (verifyViolation(matchPolicies[i]) && violationTimes<10  )
			{
				//The policy has been violated but not many times
				System.out.println("Policy " + matchPolicies[i].getName() + "has been violated " + violationTimes + "times");
				policiesViolated++;
				violated = false;
			}
			else{// The policy has not been violated
				System.out.println("Policy " + matchPolicies[i].getName() + "has not been violated ");
				violated = false;
			}
		}
		return violated;
	}
	/**
	 * @return the times a method has been violated.
	 */
	public int getViolationTimes() {
		return violationTimes;
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
	public boolean verifyViolation(Policy poly) {
		int times=0;//will keep how many times a particular policy has been violated
		boolean violated = false;
		FileReader readLog = null;
		try {
			try {
				readLog = new FileReader("log.txt");
				Scanner LogsFile = new Scanner(readLog);
				while (LogsFile.hasNextLine()) {
					String logRecord = LogsFile.nextLine();
					//Check if policy has been violated and if yes increment times.
				}
				violationTimes+= times;
				//After the while loop, add the times a particular method has been violated to the overall violation times of policies by a method.
			} finally {
				// closes the opened files
				if (readLog != null)
					readLog.close();
			}
		} catch (IOException e) {
			System.out.println("file not found");
		}
		return violated;
	}
	/**
	 * Writes the log to file after method execution whether it has successfully completed or not
	 * @param log
	 */


	public void readPolicy()
	{
		FileReader readPolicy = null;
		try {
			try {
				readPolicy = new FileReader("policies.txt");
				Scanner PolicyFile = new Scanner(readPolicy);
				while (PolicyFile.hasNextLine()) {
					String logRecord = PolicyFile.nextLine();
					//Check if policy has been violated and if yes increment times.
					String [] policy = logRecord.split("[ ]+");
					   Policy pol = new Policy();
					   Premises prem = new Premises();
					   Conclusions conc = new Conclusions();
		                  for(int i = 0; i < policy.length;i++)
		                  {
		                	  pol.setName(policy[0]);
		                	  prem.setKnowledge(Boolean.parseBoolean(policy[1]));
		                	  prem.setMethod(policy[2]);
		                	  prem.setParameters(policy[3]);
		                	  prem.setRate(Boolean.parseBoolean(policy[4]));
		                	  pol.setPremise(prem);
		                	  
		                	  conc.setMeasure(policy[5]);
		                	  conc.setKnow(Boolean.parseBoolean(policy[6]));
		                	  conc.setStatus(policy[7]);
		                	  pol.setConclusion(conc);
		                  }
				}
			} finally {
				// closes the opened files
				if (readPolicy != null)
					readPolicy.close();
			}
		} catch (IOException e) {
			System.out.println("file not found");
		}

	}



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

}
