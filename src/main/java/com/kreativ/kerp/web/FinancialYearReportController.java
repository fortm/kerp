package com.kreativ.kerp.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.kreativ.kerp.domain.EmployeeSalaryData;
import com.kreativ.kerp.domain.report.FinancialYearReport;
import com.kreativ.kerp.payment.ESICTable;
import com.kreativ.kerp.payment.PFTable;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.ReportOptions;
import com.kreativ.kerp.reference.ReportYearlyOptions;
import com.kreativ.kerp.reference.TYPE;

@RooWebScaffold(path = "financialyearreports", formBackingObject = FinancialYearReport.class)
@RequestMapping("/financialyearreports")
@Controller
public class FinancialYearReportController {
	
	
	private StringBuffer sb = null;
	private String filename = null;
	
	
    public float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }
	
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid FinancialYearReport financialYearReport, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("financialYearReport", financialYearReport);
            return "financialyearreports/create";
        }
        uiModel.asMap().clear();
        financialYearReport.persist();
        
        ReportYearlyOptions option = financialYearReport.getOptions();


        if (option.equals(ReportYearlyOptions.Consolidated_PF_Deduction_Sheet_For_Financial_Year)) {
    		
            filename = "Consolidated_PF_Statement_For_Financial_Year_"+financialYearReport.getYear()+"_Report_"+agetTimeStamp()+".csv";					

        	
			EntityManager em;

			em = EmployeeSalaryData.entityManager();
			TypedQuery<EmployeeSalaryData> q = em
					.createQuery(
							"select o from EmployeeSalaryData as o, Employee e where o.employee = e.employee_id "
									+ " and o.year = :year and o.month in ('JANUARY','FEBRUARY','MARCH','APRIL','MAY','JUNE'," +
									"'JULY','AUGUST','SEPTEMBER','OCTOBER','NOVEMBER','DECEMBER')",EmployeeSalaryData.class);
			q.setParameter("year", financialYearReport.getYear());
			 
			
			if (q.getResultList().size() == 0) {

				return "financialyearreports/createMessage";
			}

			

			
			float gross_sal = 0;
			float employer_Account_number_1 = 0 ;
			float employee_Account_number_1 = 0 ;
			float acount_number_2 = 0 ;
			float acount_number_10 = 0 ;
			float acount_number_21 = 0 ;
			float acount_number_22 = 0 ;
			
			float employer_Account_number_1_perc = 0 ;
			float employee_Account_number_1_perc = 0 ;
			float acount_number_2_perc = 0 ;
			float acount_number_10_perc = 0 ;
			float acount_number_21_perc = 0 ;
			float acount_number_22_perc = 0 ;
			
			float tot_gross_sal = 0;
			float employer_Account_number_1_tot = 0 ;
			float employee_Account_number_1_tot = 0 ;
			float acount_number_2_tot = 0 ;
			float acount_number_10_tot = 0 ;
			float acount_number_21_tot = 0 ;
			float acount_number_22_tot = 0 ;
						
			
	        PFTable pfTable = PFTable.findPFTablesByTypeEquals(TYPE.EMPLOYEE).getResultList().get(0);        
	        employee_Account_number_1_perc = pfTable.getPercentage();
	        
	        pfTable = PFTable.findPFTablesByTypeEquals(TYPE.EMPLOYER).getResultList().get(0);        
	        employer_Account_number_1_perc = pfTable.getPercentage();
	        
	        pfTable = PFTable.findPFTablesByTypeEquals(TYPE.ACCOUNT2).getResultList().get(0);        
	        acount_number_2_perc = pfTable.getPercentage();
	        
	        pfTable = PFTable.findPFTablesByTypeEquals(TYPE.ACCOUNT10).getResultList().get(0);        
	        acount_number_10_perc = pfTable.getPercentage();
	        
	        pfTable = PFTable.findPFTablesByTypeEquals(TYPE.ACCOUNT21).getResultList().get(0);        
	        acount_number_21_perc = pfTable.getPercentage();
	        
	        pfTable = PFTable.findPFTablesByTypeEquals(TYPE.ACCOUNT22).getResultList().get(0);        
	        acount_number_22_perc = pfTable.getPercentage();
	        
	        
			sb = new StringBuffer();
			sb.append(Constants.PFDataHeader);
			sb.append("\n");
			
			
			
			for (int i = 0; i < q.getResultList().size(); i++) {
				EmployeeSalaryData empSalData = q.getResultList().get(i);
			  	
				gross_sal = empSalData.getGross_salary(); 
				tot_gross_sal += gross_sal;
				

				
		        employee_Account_number_1 = roundTwoDecimals(gross_sal * employee_Account_number_1_perc/100);
		        employer_Account_number_1 = roundTwoDecimals(gross_sal * employer_Account_number_1_perc/100);
		        acount_number_2 = roundTwoDecimals(gross_sal * acount_number_2_perc/100);		        
		        acount_number_10 = roundTwoDecimals(gross_sal * acount_number_10_perc/100);
		        acount_number_21 = roundTwoDecimals(gross_sal * acount_number_21_perc/100);
		        acount_number_22 = roundTwoDecimals(gross_sal * acount_number_22_perc/100);
		        
		        employee_Account_number_1_tot += employee_Account_number_1;
		        employer_Account_number_1_tot += employer_Account_number_1;
		        acount_number_2_tot += acount_number_2;
		        acount_number_10_tot += acount_number_10;
		        acount_number_21_tot += acount_number_21;
		        acount_number_22_tot += acount_number_22;

				String str = employee_Account_number_1 + ","
						+ employer_Account_number_1 + "," + acount_number_2
						+ "," + acount_number_10 + "," + acount_number_21 + ","
						+ acount_number_22;
				
				sb.append(str+"\n");
		        
			
			}
			
			
			String str1 = roundTwoDecimals(employee_Account_number_1_tot) + ","
					+ roundTwoDecimals(employer_Account_number_1_tot) + "," + roundTwoDecimals(acount_number_2_tot)
					+ "," + roundTwoDecimals(acount_number_10_tot) + "," + roundTwoDecimals(acount_number_21_tot) + ","
					+ roundTwoDecimals(acount_number_22_tot);
			
			sb.append("\n");
			sb.append(str1);
			
        } else if (option.equals(ReportYearlyOptions.Consolidated_ESI_Deduction_Sheet_For_Financial_Year)) {
        	
    		
            filename = "Consolidated_ESI_Statement_For_Financial_Year_"+financialYearReport.getYear()+"_Report_"+agetTimeStamp()+".csv";					

        	
			EntityManager em;

			em = EmployeeSalaryData.entityManager();
			TypedQuery<EmployeeSalaryData> q = em
					.createQuery(
							"select o from EmployeeSalaryData as o, Employee e where o.employee = e.employee_id "
									+ " and o.year = :year and o.month in ('JANUARY','FEBRUARY','MARCH','APRIL','MAY','JUNE'," +
									"'JULY','AUGUST','SEPTEMBER','OCTOBER','NOVEMBER','DECEMBER')",EmployeeSalaryData.class);
			q.setParameter("year", financialYearReport.getYear());
			 
			
			if (q.getResultList().size() == 0) {

				return "financialyearreports/createMessage";
			}
			
			
			
			float gross_sal = 0;
			float employee_ESIC = 0 ;
			float employer_ESIC = 0 ;
			
			
			float employee_ESIC_perc = 0 ;
			float employer_ESIC_perc = 0 ;
			
        
			float tot_gross_sal = 0;
			float employee_ESIC_tot = 0 ;
			float employer_ESIC_tot = 0 ;
			
			
	        ESICTable esicTable = ESICTable.findESICTablesByTypeEquals(TYPE.EMPLOYEE).getResultList().get(0);
	        employee_ESIC_perc = esicTable.getPercentage();	        
	        	        
	        
	        esicTable = ESICTable.findESICTablesByTypeEquals(TYPE.EMPLOYER).getResultList().get(0);
	        employer_ESIC_perc = esicTable.getPercentage();         
	        
	        
	        sb = new StringBuffer();
			sb.append(Constants.ESIDataHeader);
			sb.append("\n");
			
			
			
			for (int i = 0; i < q.getResultList().size(); i++) {
				EmployeeSalaryData empSalData = q.getResultList().get(i);
			  	
				gross_sal = empSalData.getGross_salary(); 
				tot_gross_sal += gross_sal;
				
				
				employee_ESIC = roundTwoDecimals(gross_sal * employee_ESIC_perc/100);
				employer_ESIC = roundTwoDecimals(gross_sal * employer_ESIC_perc/100);

		        
				employee_ESIC_tot += employee_ESIC;
				employer_ESIC_tot += employer_ESIC;


				String str = employee_ESIC + ","+ employer_ESIC ;
				
				sb.append(str+"\n");
		        
			
			}
			
			
			String str1 = roundTwoDecimals(employee_ESIC_tot) + ","+ roundTwoDecimals(employer_ESIC_tot) ;
			
			sb.append("\n");
			sb.append(str1);
        }
        
        
		try {				
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			String outText = sb.toString();
			out.write(outText);
			out.close();
		} catch (IOException e) {
			System.out.println("Error happened while creating report file");
			e.printStackTrace();
		}
        
        
        
		
        
        
        
        return "redirect:/financialyearreports/" + encodeUrlPathSegment(financialYearReport.getId().toString(), httpServletRequest);
    }
    
    
    String agetTimeStamp() {
  	  DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
  	  String timeStamp = format.format(new Date());
  	  return timeStamp;
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
	
}
