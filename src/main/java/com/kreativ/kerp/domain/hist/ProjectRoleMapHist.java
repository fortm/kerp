package com.kreativ.kerp.domain.hist;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.domain.ProjectRoleMap;
import com.kreativ.kerp.reference.Mst_job_role;

@RooJavaBean
@RooToString
@RooEntity
public class ProjectRoleMapHist {
	
	
	
    public ProjectRoleMapHist(ProjectRoleMap projRoleMap) {
		super();
		this.project = projRoleMap.getProject();
		this.role = projRoleMap.getRole();
		this.basic = projRoleMap.getBasic();
		this.da = projRoleMap.getDa();
		this.special_allowance = projRoleMap.getSpecial_allowance();
		this.washing_allowance = projRoleMap.getWashing_allowance();
		this.medical_allowance = projRoleMap.getMedical_allowance();
		this.educational_allowance = projRoleMap.getEducational_allowance();
		this.conveyance_allowance = projRoleMap.getConveyance_allowance();
		this.other_allowance = projRoleMap.getOther_allowance();
		this.canteen_allowance = projRoleMap.getCanteen_allowance();
		this.ot_rate = projRoleMap.getOt_rate();
		this.ex_gratia = projRoleMap.getEx_gratia();
		this.hra_percentage = projRoleMap.getHra_percentage();
		this.service_charges = projRoleMap.getService_charges();
		this.bonus_flag = projRoleMap.isBonus_flag();
		LWF = projRoleMap.getLWF();
		this.sd = projRoleMap.getSd();
		this.atm = projRoleMap.getAtm();
	}

	@NotNull
    @ManyToOne
    private Project project;

    @NotNull
    @ManyToOne
    private Mst_job_role role;

    @NotNull
    private float basic;

    @NotNull
    private float da;

    @NotNull
    private float special_allowance;

    @NotNull
    private float washing_allowance;

    @NotNull
    private float medical_allowance;

    @NotNull
    private float educational_allowance;

    @NotNull
    private float conveyance_allowance;

    @NotNull
    private float other_allowance;
    
    @NotNull
    private float canteen_allowance;

    @NotNull
    private float ot_rate;

    @NotNull
    private float ex_gratia;

    @NotNull
    private float hra_percentage;

    @NotNull
    private float service_charges;

    @NotNull
    private boolean bonus_flag;
    
    @NotNull
    private float LWF;
    
    @NotNull
    private float sd;
    
    @NotNull
    private float atm;
    
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
