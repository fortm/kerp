package com.kreativ.kerp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.reference.Mst_job_status;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findProjectsByProject_nameEquals" })
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Integer project_id;

    @NotNull
    private String project_name;
    

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date start_date;
    
    @ManyToOne
    private Employee supervisor;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProject_name());
        return sb.toString();
    }
    
    public static TypedQuery<Project> findProjectsByProject_nameEquals(String project_name) {
        if (project_name == null || project_name.length() == 0) throw new IllegalArgumentException("The project_name argument is required");
        EntityManager em = Project.entityManager();
        TypedQuery<Project> q = em.createQuery("SELECT o FROM Project AS o WHERE o.project_name = :project_name", Project.class);
        q.setParameter("project_name", project_name);
        return q;
    }
}
