package com.kreativ.kerp.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.kreativ.kerp.reference.Mst_job_role;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findProjectRoleMapsByProjectAndRole" })
public class ProjectRoleMap {

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
}
