package com.kreativ.kerp.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.kreativ.kerp.domain.report.IndividualReport;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.IndividualReportOptions;

@RooWebScaffold(path = "individualreports", formBackingObject = IndividualReport.class)
@RequestMapping("/individualreports")
@Controller
public class IndividualReportController {
	
	private StringBuffer sb = null;
	private String filename = null;
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid IndividualReport individualReport, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("individualReport", individualReport);
            return "individualreports/create";
        }
        uiModel.asMap().clear();
        individualReport.persist();
        
        IndividualReportOptions option = individualReport.getOptions();

		if (option.equals(IndividualReportOptions.Individual_Pay_Slip_For_Each_Staff)) {
			
		   

          List<EmployeeSalaryData> empSalDataList =   EmployeeSalaryData.findEmployeeSalaryDatasByEmployeeAndYearAndMonth(individualReport.getEmployee(), individualReport.getYear(), individualReport.getMonth()).getResultList();

          if(empSalDataList.size() == 0) {
        	  
              return "individualreports/createMessage";

          }
          
			sb = new StringBuffer();
			filename = "Individual_Pay_Slip_For_Employee_"+individualReport.getEmployee().getEmployee_id()+"_YEAR_"+individualReport.getYear()+"_MONTH_"+individualReport.getMonth().toString()+"_"+agetTimeStamp()+".csv";					
			
			sb.append(Constants.EmpsalDataHeader);
			
			
			String str = empSalDataList.get(0).toString();
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
			

			
		} else if (option.equals(IndividualReportOptions.Individual_Pay_Slip__Formatted)) {
			
			
	          List<EmployeeSalaryData> empSalDataList =   EmployeeSalaryData.findEmployeeSalaryDatasByEmployeeAndYearAndMonth(individualReport.getEmployee(), individualReport.getYear(), individualReport.getMonth()).getResultList();

	          if(empSalDataList.size() == 0) {
	        	  
	              return "individualreports/createMessage";

	          }
			
			sb = new StringBuffer();
			filename = "Individual_Formatted_Pay_Slip_Employee_"+individualReport.getEmployee().getEmployee_id()+"_YEAR_"+individualReport.getYear()+"_MONTH_"+individualReport.getMonth().toString()+"_"+agetTimeStamp()+".csv";					
			
			
			sb.append(Constants.CompanyName+"\n");
			sb.append(Constants.address1+","+Constants.address2+","+Constants.address3+","+Constants.address4+","+Constants.address5+","+Constants.address6+"\n\n");
			
			sb.append("Salary Slip for "+individualReport.getMonth().toString()+","+individualReport.getYear()+"\n\n");
			
			
			EmployeeSalaryData empSalData = empSalDataList.get(0);
			
			sb.append("Name ,"+ empSalData.getEmployee().getFirst_name()+" "+empSalData.getEmployee().getLast_name() +
					",Designation,"+ 	empSalData.getEmployee().getMst_job_role()+", Project, "+ empSalData.getProject()+"\n");
			sb.append("Days payable:"+empSalData.getAttendance()+"\n\n");
			
			sb.append("Payments:\n");
			sb.append(Constants.formatPayslipHeader1+"\n");
			sb.append(empSalData.getBasic()+","+empSalData.getDa()+","+empSalData.getSpecial_allowance()+","+
					empSalData.getHra()+","+empSalData.getOther_allowance()+","+empSalData.getConveyance_allowance()+
					empSalData.getWashing_allowance()+"\n\n");
			
			
			sb.append(Constants.formatPayslipHeader2+"\n");
			sb.append(empSalData.getCanteen_allowance()+","+empSalData.getEducational_allowance()+","+empSalData.getMedical_allowance()+","
					+empSalData.getEx_gratia()+","+empSalData.getOt_days()+","+empSalData.getOt_rate()+","+empSalData.getOt()+"\n");
			
			sb.append("Gross Earnings ,"+empSalData.getGross_Earnings()+"\n");
			sb.append("Gross Salary ," +empSalData.getGross_salary()+"\n\n");
			
			sb.append("Deductions:\n");
			sb.append(Constants.formatPayslipHeader3+"\n");
			sb.append(empSalData.getPf()+","+empSalData.getEsic()+","+empSalData.getProfessional_tax()+","+empSalData.getWC()+","+empSalData.getLWF()+","
					+empSalData.getSd()+","+empSalData.getAtm()+","+"Total Deductions,"+empSalData.getTotal_deductions()+"\n");
			
			sb.append("\n");
			sb.append(Constants.formatPayslipHeader4+"\n");
			

			EntityManager em = EmployeeBankDetails.entityManager();
			TypedQuery<EmployeeBankDetails> q = em.createQuery("select o from EmployeeBankDetails as o where o.employee = :employee; ",EmployeeBankDetails.class);
			q.setParameter("employee", empSalData.getEmployee());
		
			if(q.getResultList().size() > 0){
			
			sb.append(empSalData.getNet_payment()+","+empSalData.getEmployee().getPaymentMode().toString()+","+q.getResultList().get(0).getBankName()+","
					+q.getResultList().get(0).getAccount_number()+"\n\n");
			
			}else {
				
				sb.append(empSalData.getNet_payment()+","+empSalData.getEmployee().getPaymentMode().toString()+",NILL, NILL");
							
			}
	

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
        

        
        
        return "redirect:/individualreports/" + encodeUrlPathSegment(individualReport.getId().toString(), httpServletRequest);
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
