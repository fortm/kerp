package com.kreativ.kerp.payment;

import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class ProfTaxTable {
	
	@NotNull
	private float fromAmount;
	
	@NotNull
	private float toAmount;
	
	@NotNull
	private float profTax;
		
	
}
