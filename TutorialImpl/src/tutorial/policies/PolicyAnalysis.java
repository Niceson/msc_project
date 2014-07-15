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
		boolean violated = false;
		for (int i = 0; i < matchPolicies.length; i++) {
			matchPolicies[i].getConclusion(); //if such a method has been violated,
			
			//returns the times which the method has been violated
			violationTimes += setViolationTimes(matchPolicies[i]);//find out how many times it has been violated from the log history
			
			violated= true;
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
	public int setViolationTimes(Policy poly) {
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

}
