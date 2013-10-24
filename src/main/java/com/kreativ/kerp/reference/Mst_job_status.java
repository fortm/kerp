package com.kreativ.kerp.reference;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findMst_job_statusesByJob_statusEquals" })
public class Mst_job_status {

    public Mst_job_status(String job_status) {
        super();
        this.job_status = job_status;
    }

    @NotNull
    @Column(unique = true)
    private String job_status;
}
