package tutorial.policies;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PolicyAnalysis {
	PolicyCollection collect = new PolicyCollection();
	public PolicyAnalysis()
	{
	}
	/**
	 * Returns applicable policies related to a particular method and checks if
	 * any has been violated if any has, it calls the method to verify the
	 * history log.
	 * 
	 * @param method
	 */
	public boolean Analyse(String method, String params) {
		Policy[] matchPolicies = collect.getMyPolicies(method,params);
		boolean violated = false;
		for (int i = 0; i < matchPolicies.length; i++) {
			if (matchPolicies[i].isViolated()) {//if such a method has been violated,
				verifyLog(matchPolicies[i]);//find out how many times it has been violated from the log history
				violated= true;
			}
		}
		return violated;
	}

	/**
	 * Verifies from the log file (log.txt) how many times
	 * a certain policy related to a method has been violated by
	 * a given class in the past
	 * This will help us determine the security measures to be taken
	 * depending on the previous interactions
	 * @param poly the policy that has been violated
	 * @param method the method to which this policy is applied
	 * @return
	 */
	public int verifyLog(Policy poly) {
		int times=0;
		FileReader readLog = null;
		try {
			try {
				readLog = new FileReader("log.txt");
				Scanner LogsFile = new Scanner(readLog);
				while (LogsFile.hasNextLine()) {
					String logRecord = LogsFile.nextLine();
				}
			} finally {
				// closes the opened files
				if (readLog != null)
					readLog.close();
			}
		} catch (IOException e) {
			System.out.println("file not found");
		}
		return times;
	}

}
