package com.kreativ.kerp.web;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeSalaryData;
import com.kreativ.kerp.domain.MonthsInYear;
import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.domain.hist.EmployeeHist;
import com.kreativ.kerp.domain.hist.EmployeeProjectMapHist;
import com.kreativ.kerp.domain.hist.ProjectRoleMapHist;
import com.kreativ.kerp.payment.ESICTable;
import com.kreativ.kerp.payment.PFTable;
import com.kreativ.kerp.payment.ProfTaxTable;
import com.kreativ.kerp.payment.WCTable;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.ReportingCode;
import com.kreativ.kerp.reference.TYPE;
import com.kreativ.kerp.time.Timesheet;

@RooWebScaffold(path = "employeesalarydatas", formBackingObject = EmployeeSalaryData.class, update = false)
@RequestMapping("/employeesalarydatas")
@Controller
public class EmployeeSalaryDataController {

    public float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid EmployeeSalaryData employeeSalaryData, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("employeeSalaryData", employeeSalaryData);
            return "employeesalarydatas/create";
        }
        EntityManager em;
        EmployeeSalaryData obj = employeeSalaryData;
        Employee emp = obj.getEmployee();
        
        em = EmployeeSalaryData.entityManager();
        TypedQuery<EmployeeSalaryData> q7 = em.createQuery("select e from EmployeeSalaryData as e where " +
        		"e.employee = :employee and e.year = :year and e.month = :month", EmployeeSalaryData.class);
        q7.setParameter("employee", obj.getEmployee());
        q7.setParameter("year", obj.getYear());
        q7.setParameter("month", obj.getMonth());
        
        if(q7.getResultList().size() > 0 ) {
        	
        	return "employeesalarydatas/createMessage1";
        }
        
    	HashMap<String,Integer> monthsMap = new HashMap<String,Integer>(); 	
    	monthsMap.put("JANUARY", new Integer(0));
    	monthsMap.put("FEBRUARY", new Integer(1));
    	monthsMap.put("MARCH", new Integer(2));
    	monthsMap.put("APRIL", new Integer(3));
    	monthsMap.put("MAY", new Integer(4));
    	monthsMap.put("JUNE", new Integer(5));
    	monthsMap.put("JULY", new Integer(6));
    	monthsMap.put("AUGUST", new Integer(7));
    	monthsMap.put("SEPTEMBER", new Integer(8));
    	monthsMap.put("OCTOBER", new Integer(9));
    	monthsMap.put("NOVEMBER", new Integer(10));
    	monthsMap.put("DECEMBER", new Integer(11));
  
        boolean found = false;
        em = EmployeeHist.entityManager();
        
        TypedQuery<EmployeeHist> q4 = em.createQuery("select e from EmployeeHist as e " +
        		"where e.employee = :employee order by e.year,e.month", EmployeeHist.class);
        q4.setParameter("employee", emp);
        for (int j = 0; j < q4.getResultList().size(); j++) {
            EmployeeHist empHist = q4.getResultList().get(j);
           
            /*Calendar cal = Calendar.getInstance();
            cal.setTime(empHist.getCreated());
            int hist_month = cal.get(Calendar.MONTH);
            int hist_year = cal.get(Calendar.YEAR);
            */
            
            //System.out.println("history = "+ monthsMap.get(empHist.getMonth().toString()) + "  Input = "+ monthsMap.get(obj.getMonth().toString())); 
             
            
            if ( ( monthsMap.get(empHist.getMonth().toString()) < monthsMap.get(obj.getMonth().toString()) || 
            		empHist.getMonth().equals(obj.getMonth()) )  && empHist.getYear() <= Integer.parseInt(obj.getYear())) {
                obj.setRole(EmployeeHist.findMst_job_roleBy_Id(empHist).toString());
                obj.setProject(empHist.getProject().toString());
                found = true;
            }
        }
        
        //System.out.println("-----------");
        
        
        if(!found) {
        	
            return "employeesalarydatas/createMessage2";
        	
        }
        
        /*em = EmployeeProjectMapHist.entityManager();
        TypedQuery<EmployeeProjectMapHist> q5 = em.createQuery("select e from EmployeeProjectMapHist as e " +
        		"where e.employee = :employee", EmployeeProjectMapHist.class);
        q5.setParameter("employee", emp);
        for (int j = 0; j < q5.getResultList().size(); j++) {
            EmployeeProjectMapHist empProjMapHist = q5.getResultList().get(j);
            Calendar cal = Calendar.getInstance();
            cal.setTime(empProjMapHist.getCreated());
            int hist_month = cal.get(Calendar.MONTH);
            int hist_year = cal.get(Calendar.YEAR);
            if (Months.get(hist_month).equals(obj.getMonth()) && hist_year == Integer.parseInt(obj.getYear())) {
                obj.setProject(empProjMapHist.getProject().toString());
            }
        }*/
        
        float sum_overtime_hrs = 0;
        float sum_working_hrs = 0;
        int MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        
        
        em = MonthsInYear.entityManager();
        TypedQuery<MonthsInYear> q3 = em.createQuery("select m from MonthsInYear as m where m.year = :year " +
        		"and m.months = :months", MonthsInYear.class);
        q3.setParameter("year", obj.getYear());
        q3.setParameter("months", obj.getMonth());
        
        if(q3.getResultList().size()==0) {
            return "employeesalarydatas/createMessage4";
        }
        
        MonthsInYear monthsInYear = q3.getResultList().get(0);
        float daysinMonth = (monthsInYear.getTo_date().getTime() - monthsInYear.getFrom_date().getTime() ) / MILLSECS_PER_DAY + 1;
        
        
        em = Timesheet.entityManager();
        TypedQuery<Timesheet> q1 = em.createQuery("select t from Timesheet as t, Employee as e , MonthsInYear as m where t.employee = e.employee_id " 
        + " and t.date >= m.from_date and t.date <= m.to_date and e.employee_id = :employee_id and m.months = :months and m.year = :year", Timesheet.class);
        q1.setParameter("employee_id", emp.getEmployee_id());
        q1.setParameter("months", obj.getMonth());
        q1.setParameter("year", obj.getYear());
                
        
        int i = 0, daysAttended = 0;
        for (i = 0; i < q1.getResultList().size(); i++) {
            Timesheet timeSheetTable = q1.getResultList().get(i);
            sum_overtime_hrs += timeSheetTable.getOvertime_hours();
            sum_working_hrs += timeSheetTable.getWorking_hours();
            if (timeSheetTable.getReporting_code().equals(ReportingCode.P)) daysAttended++;
        }

        
        if (i < daysinMonth) {
            return "employeesalarydatas/showMessage";
        }

        
        obj.setAttendance(daysAttended);
        float denominator = daysinMonth * Constants.IDEAL_HRS;
        float numerator = sum_working_hrs;
        float multiplicationFactor = numerator / denominator;
        
        
        em = ProjectRoleMapHist.entityManager();
        TypedQuery<ProjectRoleMapHist> q6 = em.createQuery("select p from ProjectRoleMapHist as p where " +
        		"p.project = :project and p.role = :role", ProjectRoleMapHist.class);
        q6.setParameter("project", Project.findProjectsByProject_nameEquals(obj.getProject()).getResultList().get(0));
        q6.setParameter("role", Mst_job_role.findMst_job_rolesByJob_roleEquals(obj.getRole()));
        ProjectRoleMapHist projectRoleMap = null;
        for (int j = 0; j < q6.getResultList().size(); j++) {
            projectRoleMap = q6.getResultList().get(j);
            /*Calendar cal = Calendar.getInstance();
            cal.setTime(projectRoleMap.getCreated());
            int hist_month = cal.get(Calendar.MONTH);
            int hist_year = cal.get(Calendar.YEAR);
            if (Months.get(hist_month).equals(obj.getMonth()) && hist_year == Integer.parseInt(obj.getYear())) {
                obj.setProject(projectRoleMap.getProject().toString());
            }
            */
        }
        
        if(q6.getResultList().size() == 0 ) {
        	 return "employeesalarydatas/createMessage3";
        }
        	 
        
        obj.setBasic(roundTwoDecimals(projectRoleMap.getBasic() * multiplicationFactor));
        obj.setDa(roundTwoDecimals(projectRoleMap.getDa() * multiplicationFactor));
        obj.setWashing_allowance(roundTwoDecimals(projectRoleMap.getWashing_allowance() * multiplicationFactor));
        obj.setBonus_flag(projectRoleMap.isBonus_flag());
        obj.setSpecial_allowance(roundTwoDecimals(projectRoleMap.getSpecial_allowance() * multiplicationFactor));
        obj.setService_charges(roundTwoDecimals(projectRoleMap.getService_charges() * multiplicationFactor));
        obj.setConveyance_allowance(roundTwoDecimals(projectRoleMap.getConveyance_allowance() * multiplicationFactor));
        obj.setEducational_allowance(roundTwoDecimals(projectRoleMap.getEducational_allowance() * multiplicationFactor));
        obj.setEx_gratia(roundTwoDecimals(projectRoleMap.getEx_gratia() * multiplicationFactor));
        obj.setHra_percentage(roundTwoDecimals(projectRoleMap.getHra_percentage()));
        obj.setMedical_allowance(roundTwoDecimals(projectRoleMap.getMedical_allowance() * multiplicationFactor));
        obj.setOt_rate(roundTwoDecimals(projectRoleMap.getOt_rate()));
        obj.setOther_allowance(roundTwoDecimals(projectRoleMap.getOther_allowance() * multiplicationFactor));
        obj.setCanteen_allowance(roundTwoDecimals(projectRoleMap.getCanteen_allowance() * multiplicationFactor));
        obj.asetGross_salary();
        obj.asetHra();
        obj.setOt_days(sum_overtime_hrs);
        obj.asetOt(sum_overtime_hrs);
        obj.asetGross_Earnings();
        
        
        em = WCTable.entityManager();
        TypedQuery<WCTable> q = em.createQuery("SELECT o FROM WCTable AS o", WCTable.class);
        
        if(q.getResultList().size() == 0 ) {
        	return "employeesalarydatas/createMessage5";
        }
        
        WCTable wcTable = q.getResultList().get(0);
        obj.asetWC(wcTable.getPercentage());
        
        TYPE employeeType = TYPE.EMPLOYEE;
        PFTable pfTable = PFTable.findPFTablesByTypeEquals(employeeType).getResultList().get(0);        
        
        if(emp.getMst_job_status().getId() != 2 )
        obj.asetPf(pfTable.getPercentage());
                
        ESICTable esicTable = ESICTable.findESICTablesByTypeEquals(employeeType).getResultList().get(0);
        
        if(emp.getMst_job_status().getId() != 2 )
        obj.asetEsic(esicTable.getPercentage());
        
        
        em = ProfTaxTable.entityManager();
        TypedQuery<ProfTaxTable> q2 = em.createQuery("SELECT o FROM ProfTaxTable AS o", ProfTaxTable.class);
        
        if(q2.getResultList().size() == 0 ) {
        	return "employeesalarydatas/createMessage6";
        }
        
        
        for (i = 0; i < q2.getResultList().size(); i++) {
            ProfTaxTable profTaxTable = q2.getResultList().get(i);
            if (obj.getGross_salary() >= profTaxTable.getFromAmount() && obj.getGross_salary() <= profTaxTable.getToAmount()) {
                obj.asetProfessional_tax(profTaxTable.getProfTax());
                break;
            }
        }
        
        obj.setLWF(roundTwoDecimals(projectRoleMap.getLWF()));
        obj.setSd(roundTwoDecimals(projectRoleMap.getSd()));
        obj.setAtm(roundTwoDecimals(projectRoleMap.getAtm()));
        obj.asetTotal_deductions();
        obj.asetNet_payment();
        obj.persist();
        uiModel.asMap().clear();
        return "redirect:/employeesalarydatas/" + encodeUrlPathSegment(employeeSalaryData.getId().toString(), httpServletRequest);
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("employeesalarydata", EmployeeSalaryData.findEmployeeSalaryData(id));
        uiModel.addAttribute("itemId", id);
        return "employeesalarydatas/show";
    }
}
