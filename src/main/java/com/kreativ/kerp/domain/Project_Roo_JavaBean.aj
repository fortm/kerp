// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.Employee;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

privileged aspect Project_Roo_JavaBean {
    
    public Integer Project.getProject_id() {
        return this.project_id;
    }
    
    public void Project.setProject_id(Integer project_id) {
        this.project_id = project_id;
    }
    
    public String Project.getProject_name() {
        return this.project_name;
    }
    
    public void Project.setProject_name(String project_name) {
        this.project_name = project_name;
    }
    
    public Date Project.getStart_date() {
        return this.start_date;
    }
    
    public void Project.setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    
    public Employee Project.getSupervisor() {
        return this.supervisor;
    }
    
    public void Project.setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
    
}
