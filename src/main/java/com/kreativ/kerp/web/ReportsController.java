package com.kreativ.kerp.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

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

import com.kreativ.kerp.domain.EmployeeBankDetails;
import com.kreativ.kerp.domain.EmployeeSalaryData;
import com.kreativ.kerp.domain.report.Reports;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.ReportOptions;

@RooWebScaffold(path = "reportses", formBackingObject = Reports.class)
@RequestMapping("/reportses")
@Controller
public class ReportsController {
	
	private StringBuffer sb = null;
	private String filename = null;
	
    public float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Reports reports, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("reports", reports);
            return "reportses/create";
        }
        uiModel.asMap().clear();
        reports.persist();
        
		
            ReportOptions option = reports.getOptions();
        

			// CONSOLIDATED REPORT REQUEST

			

			if (option.equals(ReportOptions.Consolidated_Salary_Statement_for_Permanent_Staff)) {

		    EntityManager em;

			em = EmployeeSalaryData.entityManager();
			TypedQuery<EmployeeSalaryData> q = em
					.createQuery(
							"select o from EmployeeSalaryData as o, Employee e, Mst_job_status m where o.employee = e.employee_id "
									+ "and e.mst_job_status = m.id and  m.job_status = 'PERMANENT' and o.year = :year and o.month = :month",
							EmployeeSalaryData.class);
			q.setParameter("year", reports.getYear());
			q.setParameter("month", reports.getMonth());
			 
			//System.out.println(reports.getYear()+ "  "+  reports.getMonth().toString()); 
			
			if (q.getResultList().size() == 0) {

				return "reportses/createMessage1";
			}

			Calendar cal = Calendar.getInstance();
								
			
			sb = new StringBuffer();
			filename = "Consolidated_Permanent_Employee_Salary_Report_YEAR_"+reports.getYear()+"_MONTH_"+reports.getMonth().toString()+"_"+agetTimeStamp()+".csv";					
			
			sb.append(Constants.EmpsalDataHeader);
			
			float atmTot = 0;
			float basic = 0;
			float canteen = 0;
			float conveyance = 0;
			float da = 0;
			float education = 0;
			float esic = 0;
			float exgratia = 0;
			float gross_earn = 0;
			float gross_sal = 0;
			float hra = 0;
			float lwf = 0;
			float medical = 0;
			float net_payment = 0;
			float ot = 0;
			float other = 0;
			float pf = 0;
			float prof_tax = 0;
			float sd = 0;
			float service_charges = 0;
			float special = 0;
			float tot_ded = 0;
			float wc = 0;
			float washing = 0;

			String dummy ="";

			String totString = "";
			                                 
			
			for (int i = 0; i < q.getResultList().size(); i++) {
				EmployeeSalaryData empSalData = q.getResultList().get(i);
				
				
				atmTot += empSalData.getAtm();
				basic += empSalData.getBasic();
				canteen += empSalData.getCanteen_allowance();
				conveyance += empSalData.getConveyance_allowance();
				da += empSalData.getDa();
				education += empSalData.getEducational_allowance();
				esic += empSalData.getEsic();
				exgratia += empSalData.getEx_gratia();
				gross_earn += empSalData.getGross_Earnings();
				gross_sal += empSalData.getGross_salary();
				hra += empSalData.getHra();
				lwf += empSalData.getLWF();
				medical += empSalData.getMedical_allowance();
				net_payment = empSalData.getNet_payment();
				ot += empSalData.getOt();
				other += empSalData.getOther_allowance();
				pf += empSalData.getPf();
				prof_tax += empSalData.getProfessional_tax();
				sd  += empSalData.getSd();
				service_charges += empSalData.getService_charges();
				special += empSalData.getSpecial_allowance();
				tot_ded += empSalData.getTotal_deductions();
				wc += empSalData.getWC();
				washing += empSalData.getWashing_allowance();
				
				
								
				
				
				String str = empSalData.toString();
				String str2 = new String();
				
				StringTokenizer token = new StringTokenizer(str,",");
				while(token.hasMoreTokens()) {
					
					String str1 = token.nextToken();
					
					StringTokenizer token1 = new StringTokenizer(str1,":");
				
					String key = token1.nextToken();
					String value = token1.nextToken();
				    
					str2 += value+","; 														
				
				}
				
				str2 = str2.substring(0, str2.lastIndexOf(',')); 
				
				sb.append("\n");
				sb.append(str2) ; 
				
			}
			
			 totString = roundTwoDecimals(atmTot) +",," +roundTwoDecimals(basic)+","+roundTwoDecimals(canteen)+","+roundTwoDecimals(conveyance)+","+
					 roundTwoDecimals(da)+","+roundTwoDecimals(education)+",,"+roundTwoDecimals(esic)+","+roundTwoDecimals(exgratia)+","+roundTwoDecimals(gross_earn)+","+roundTwoDecimals(gross_sal)+
					 ","+roundTwoDecimals(hra)+",,,"+roundTwoDecimals(lwf)+","+roundTwoDecimals(medical)+",,"+roundTwoDecimals(net_payment)+","+roundTwoDecimals(ot)+",,,"+
					 roundTwoDecimals(other)+","+roundTwoDecimals(pf)+","+roundTwoDecimals(prof_tax)+","+",,"+roundTwoDecimals(sd)+","+roundTwoDecimals(service_charges)+
                     ","+roundTwoDecimals(special)+","+roundTwoDecimals(tot_ded)+",,"+roundTwoDecimals(wc)+","+roundTwoDecimals(washing)+",,";
			 
			 sb.append("\n\n");
			 sb.append(totString);
						
			
		} else if (option.equals(ReportOptions.Consolidated_Salary_Statement_for_Temporary_Staff_paid_by_Cash)) {
			
			EntityManager em;

			em = EmployeeSalaryData.entityManager();
			TypedQuery<EmployeeSalaryData> q = em
					.createQuery(
							"select o from EmployeeSalaryData as o, Employee e, Mst_job_status m where o.employee = e.employee_id "
									+ "and e.mst_job_status = m.id and  m.job_status = 'TEMPORARY' and e.paymentMode = 0 and o.year = :year and o.month = :month",
							EmployeeSalaryData.class);
			q.setParameter("year", reports.getYear());
			q.setParameter("month", reports.getMonth());
			 
			//System.out.println(reports.getYear()+ "  "+  reports.getMonth().toString()); 
			
			if (q.getResultList().size() == 0) {

				return "reportses/createMessage1";
			}
			
            Calendar cal = Calendar.getInstance();
            
            float atmTot = 0;
			float basic = 0;
			float canteen = 0;
			float conveyance = 0;
			float da = 0;
			float education = 0;
			float esic = 0;
			float exgratia = 0;
			float gross_earn = 0;
			float gross_sal = 0;
			float hra = 0;
			float lwf = 0;
			float medical = 0;
			float net_payment = 0;
			float ot = 0;
			float other = 0;
			float pf = 0;
			float prof_tax = 0;
			float sd = 0;
			float service_charges = 0;
			float special = 0;
			float tot_ded = 0;
			float wc = 0;
			float washing = 0;

			String dummy ="";

			String totString = "";
			                                 
								
			
			sb = new StringBuffer();
			filename = "Consolidated_Temporary_Employee_PaidBycash_Salary_Report_YEAR_"+reports.getYear()+"_MONTH_"+reports.getMonth().toString()+"_"+agetTimeStamp()+".csv";					
			
			sb.append(Constants.EmpsalDataHeader);
			
			
			for (int i = 0; i < q.getResultList().size(); i++) {
				EmployeeSalaryData empSalData = q.getResultList().get(i);
				
				atmTot += empSalData.getAtm();
				basic += empSalData.getBasic();
				canteen += empSalData.getCanteen_allowance();
				conveyance += empSalData.getConveyance_allowance();
				da += empSalData.getDa();
				education += empSalData.getEducational_allowance();
				esic += empSalData.getEsic();
				exgratia += empSalData.getEx_gratia();
				gross_earn += empSalData.getGross_Earnings();
				gross_sal += empSalData.getGross_salary();
				hra += empSalData.getHra();
				lwf += empSalData.getLWF();
				medical += empSalData.getMedical_allowance();
				net_payment = empSalData.getNet_payment();
				ot += empSalData.getOt();
				other += empSalData.getOther_allowance();
				pf += empSalData.getPf();
				prof_tax += empSalData.getProfessional_tax();
				sd  += empSalData.getSd();
				service_charges += empSalData.getService_charges();
				special += empSalData.getSpecial_allowance();
				tot_ded += empSalData.getTotal_deductions();
				wc += empSalData.getWC();
				washing += empSalData.getWashing_allowance();
				
				
				String str = empSalData.toString();
				String str2 = new String();
				
				StringTokenizer token = new StringTokenizer(str,",");
				while(token.hasMoreTokens()) {
					
					String str1 = token.nextToken();
					
					StringTokenizer token1 = new StringTokenizer(str1,":");
				
					String key = token1.nextToken();
					String value = token1.nextToken();
				    
					str2 += value+","; 														
				
				}
				
				str2 = str2.substring(0, str2.lastIndexOf(',')); 
				
				sb.append("\n");
				sb.append(str2) ; 
				
			}
			
			 totString = roundTwoDecimals(atmTot) +",," +roundTwoDecimals(basic)+","+roundTwoDecimals(canteen)+","+roundTwoDecimals(conveyance)+","+roundTwoDecimals(da)+","+
					 roundTwoDecimals(education)+",,"+roundTwoDecimals(esic)+","+roundTwoDecimals(exgratia)+","+roundTwoDecimals(gross_earn)+","+roundTwoDecimals(gross_sal)+","+
                     hra+",,,"+lwf+","+medical+",,"+net_payment+","+ot+",,,"+other+","+pf+","+prof_tax+","+",,"+sd+","+service_charges+
                     ","+roundTwoDecimals(special)+","+roundTwoDecimals(tot_ded)+",,"+roundTwoDecimals(wc)+","+roundTwoDecimals(washing)+",,";
			 
			 sb.append("\n\n");
			 sb.append(totString);

			

		} else if (option.equals(ReportOptions.Consolidated_Bank_Statement_for_Permanent_And_Contractual_Staff)) {
			
		
			EntityManager em;

			em = EmployeeBankDetails.entityManager();
			TypedQuery<EmployeeBankDetails> q = em
					.createQuery(
							"select o from EmployeeBankDetails o, EmployeeSalaryData as s, Employee e, Mst_job_status m where o.employee = e.employee_id "+
					                " and s.employee = e.employee_id and o.employee = s.employee and e.mst_job_status = m.id and  m.job_status in ('PERMANENT','CONTRACTUAL') " +
									" and s.year = :year and s.month = :month",
									EmployeeBankDetails.class);
			q.setParameter("year", reports.getYear());
			q.setParameter("month", reports.getMonth());
			
			
			if (q.getResultList().size() == 0) {

				return "reportses/createMessage1";
			}
			
			em = EmployeeSalaryData.entityManager();
			TypedQuery<EmployeeSalaryData> q1 = em
					.createQuery(
							"select s from EmployeeBankDetails o, EmployeeSalaryData as s, Employee e, Mst_job_status m where o.employee = e.employee_id "+
					                " and s.employee = e.employee_id and o.employee = s.employee and e.mst_job_status = m.id and  m.job_status in ('PERMANENT','CONTRACTUAL') " +
									" and s.year = :year and s.month = :month",
									EmployeeSalaryData.class);
			q1.setParameter("year", reports.getYear());
			q1.setParameter("month", reports.getMonth());
			
			
			
			if (q1.getResultList().size() == 0) {

				return "reportses/createMessage1";
			} 
			
			float salTotal = 0;
			String totString = "";
			
			
            sb = new StringBuffer();
            filename = "Consolidated_Bank_Statement_For_Permanent_And_Contractual_Staff_Report_YEAR_"+reports.getYear()+"_MONTH_"+reports.getMonth().toString()+"_"+agetTimeStamp()+".csv";					

            sb.append(Constants.EmpBankDataHeader);			
			
			
			for (int i = 0; i < q.getResultList().size(); i++) {
				EmployeeBankDetails empBankData = q.getResultList().get(i);
				EmployeeSalaryData empSalData = q1.getResultList().get(i);
				
				salTotal += empSalData.getNet_payment();
				
				String str = empBankData.toString();
				String str2 = new String();
				//System.out.println(str); 
				//System.out.println(empSalData.getNet_payment()); 
				
				StringTokenizer token = new StringTokenizer(str,",");
				while(token.hasMoreTokens()) {
					
					String str1 = token.nextToken();
					
					StringTokenizer token1 = new StringTokenizer(str1,":");
				
					String key = token1.nextToken();
					String value = "";
					if(token.hasMoreTokens())
					   value = token1.nextToken();										
				    
					str2 += value+","; 														
				
				}
				
				str2 = str2.substring(0, str2.lastIndexOf(',')); 
				
				sb.append("\n");
				sb.append(str2).append(",").append(empSalData.getNet_payment()) ;  
				
			}
			
			totString = ",,,,,,"+roundTwoDecimals(salTotal);
			
			sb.append("\n\n");
			sb.append(totString);
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
        
        
        return "redirect:/reportses/" + encodeUrlPathSegment(reports.getId().toString(), httpServletRequest);
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
    
    String agetTimeStamp() {
    	  DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
    	  String timeStamp = format.format(new Date());
    	  return timeStamp;
    }
    
    
	
}
