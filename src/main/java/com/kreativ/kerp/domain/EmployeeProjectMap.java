package com.kreativ.kerp.domain;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findEmployeeProjectMapsByEmployee" })
public class EmployeeProjectMap {

    @ManyToOne
    private Project project;

    @ManyToOne
    private Employee employee;
}
