// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.report;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.reference.IndividualReportOptions;
import com.kreativ.kerp.reference.Months;
import java.lang.String;

privileged aspect IndividualReport_Roo_JavaBean {
    
    public Employee IndividualReport.getEmployee() {
        return this.employee;
    }
    
    public void IndividualReport.setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String IndividualReport.getYear() {
        return this.year;
    }
    
    public void IndividualReport.setYear(String year) {
        this.year = year;
    }
    
    public Months IndividualReport.getMonth() {
        return this.month;
    }
    
    public void IndividualReport.setMonth(Months month) {
        this.month = month;
    }
    
    public IndividualReportOptions IndividualReport.getOptions() {
        return this.Options;
    }
    
    public void IndividualReport.setOptions(IndividualReportOptions Options) {
        this.Options = Options;
    }
    
}
