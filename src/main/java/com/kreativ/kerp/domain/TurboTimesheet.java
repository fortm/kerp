package com.kreativ.kerp.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.reference.Months;

@RooJavaBean
@RooToString
@RooEntity
public class TurboTimesheet {
	
	@ManyToOne
	private Employee employee;
	
	@NotNull
	private int year;
	
	@NotNull
	private Months month;
			
}
