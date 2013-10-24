package com.kreativ.kerp.payment;

import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class WCTable {
	
	@NotNull
	private float percentage;
	
    public float getPercentage() {
        return this.percentage;
    }
}
