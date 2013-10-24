package com.kreativ.kerp.payment;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.reference.TYPE;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findPFTablesByTypeEquals" })
public class PFTable {

    @NotNull
    @Column(unique = true)
    @Enumerated
    private TYPE type;

    @NotNull
    private float percentage;
}
