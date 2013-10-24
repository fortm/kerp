package com.kreativ.kerp.domain.hist;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeProjectMap;
import com.kreativ.kerp.domain.Project;

@RooJavaBean
@RooToString
@RooEntity
public class EmployeeProjectMapHist {
	
	
	
    public EmployeeProjectMapHist(EmployeeProjectMap empProjMap) {
		super();
		this.project = empProjMap.getProject();
		this.employee = empProjMap.getEmployee();
	}

	@ManyToOne
    private Project project;

    @ManyToOne
    private Employee employee;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(updatable = false)
    private Date created;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(updatable = true)
    private Date lastUpdated;
    
    @PrePersist
    public void auditCreatedTime() {
        this.created=new java.util.Date();
    }
    
    @PreUpdate
    public void auditUpdatedTime() {
        this.lastUpdated=new java.util.Date();
    }
    
    
    public Date getCreated() {
        return this.created;
    }
    
    
    
}
