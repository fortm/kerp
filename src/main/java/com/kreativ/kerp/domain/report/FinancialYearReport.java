package com.kreativ.kerp.domain.report;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.reference.ReportYearlyOptions;

@RooJavaBean
@RooToString
@RooEntity
public class FinancialYearReport {
	
	@NotNull
	private String year;
	
    @NotNull
	@Enumerated
	private ReportYearlyOptions Options;
}
