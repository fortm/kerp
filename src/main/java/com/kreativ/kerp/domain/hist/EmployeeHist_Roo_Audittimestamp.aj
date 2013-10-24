// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.hist;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect EmployeeHist_Roo_Audittimestamp {
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(updatable = false)
    private Date EmployeeHist.created;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(updatable = true)
    private Date EmployeeHist.lastUpdated;
    
    @PrePersist
    public void EmployeeHist.auditCreatedTime() {
        this.created=new java.util.Date();
    }
    
    @PreUpdate
    public void EmployeeHist.auditUpdatedTime() {
        this.lastUpdated=new java.util.Date();
    }
    
    public Date EmployeeHist.getCreated() {
        return this.created;
    }
    
    
}
