// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.hist;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.WorkArea;

privileged aspect EmployeeHist_Roo_JavaBean {
    
    public Employee EmployeeHist.getEmployee() {
        return this.employee;
    }
    
    public void EmployeeHist.setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public int EmployeeHist.getYear() {
        return this.year;
    }
    
    public void EmployeeHist.setYear(int year) {
        this.year = year;
    }
    
    public Months EmployeeHist.getMonth() {
        return this.month;
    }
    
    public void EmployeeHist.setMonth(Months month) {
        this.month = month;
    }
    
    public WorkArea EmployeeHist.getWorkArea() {
        return this.workArea;
    }
    
    public void EmployeeHist.setWorkArea(WorkArea workArea) {
        this.workArea = workArea;
    }
    
    public Project EmployeeHist.getProject() {
        return this.project;
    }
    
    public void EmployeeHist.setProject(Project project) {
        this.project = project;
    }
    
    public Mst_job_role EmployeeHist.getJob_role() {
        return this.job_role;
    }
    
    public void EmployeeHist.setJob_role(Mst_job_role job_role) {
        this.job_role = job_role;
    }
    
}