// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.report;

import com.kreativ.kerp.reference.ReportYearlyOptions;
import java.lang.String;

privileged aspect FinancialYearReport_Roo_JavaBean {
    
    public String FinancialYearReport.getYear() {
        return this.year;
    }
    
    public void FinancialYearReport.setYear(String year) {
        this.year = year;
    }
    
    public ReportYearlyOptions FinancialYearReport.getOptions() {
        return this.Options;
    }
    
    public void FinancialYearReport.setOptions(ReportYearlyOptions Options) {
        this.Options = Options;
    }
    
}