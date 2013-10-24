package com.kreativ.kerp.reference;

public class Global {

	private static String[] reportNames;

	public static String[] getReportNames() {
		return reportNames;
	}

	public static void setReportNames(String[] reportNames) {
		reportNames = new String[2];
		reportNames[0] = "Consolidated Salary Statement for Permanent Staff" ;
	    reportNames[1] = "Consolidated Salary Statement for Temporary Staff paid by Cash" ;		
	}
	
	
	
}
