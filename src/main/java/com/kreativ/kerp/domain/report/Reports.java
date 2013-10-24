package com.kreativ.kerp.domain.report;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.ReportOptions;

@RooJavaBean
@RooToString
@RooEntity
public class Reports {

	private String year;

	
	@Enumerated
	private Months month;

    @NotNull
	@Enumerated
	private ReportOptions Options;

}