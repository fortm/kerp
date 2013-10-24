package com.kreativ.kerp.domain.report;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.reference.IndividualReportYearlyOptions;

@RooJavaBean
@RooToString
@RooEntity
public class IndividualFinancialReport {
	
	@ManyToOne
	private Employee employee;
	
	@NotNull
	private String year;
	
    @NotNull
	@Enumerated
	private IndividualReportYearlyOptions Options;
	
}
