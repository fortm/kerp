// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.reference.Months;

privileged aspect TurboTimesheet_Roo_JavaBean {
    
    public Employee TurboTimesheet.getEmployee() {
        return this.employee;
    }
    
    public void TurboTimesheet.setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public int TurboTimesheet.getYear() {
        return this.year;
    }
    
    public void TurboTimesheet.setYear(int year) {
        this.year = year;
    }
    
    public Months TurboTimesheet.getMonth() {
        return this.month;
    }
    
    public void TurboTimesheet.setMonth(Months month) {
        this.month = month;
    }
    
}
