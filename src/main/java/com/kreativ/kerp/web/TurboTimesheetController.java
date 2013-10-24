package com.kreativ.kerp.web;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.kreativ.kerp.domain.MonthsInYear;
import com.kreativ.kerp.domain.TurboTimesheet;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.ReportingCode;
import com.kreativ.kerp.time.Timesheet;

@RooWebScaffold(path = "turbotimesheets", formBackingObject = TurboTimesheet.class)
@RequestMapping("/turbotimesheets")
@Controller
public class TurboTimesheetController {
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid TurboTimesheet turboTimesheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("turboTimesheet", turboTimesheet);
            return "turbotimesheets/create";
        }
        uiModel.asMap().clear();
        turboTimesheet.persist();
        
        if(turboTimesheet.getYear() == 0 ){
        	 return "turbotimesheets/createMessage1";
        }
        
        EntityManager em = null;
        
        em = Timesheet.entityManager();
        
        TypedQuery<Timesheet> q1 = em.createQuery("select t from Timesheet as t, Employee as e , MonthsInYear as m where t.employee = e.employee_id " 
        + " and t.date >= m.from_date and t.date <= m.to_date and e.employee_id = :employee_id and m.months = :months and m.year = :year", Timesheet.class);
        q1.setParameter("employee_id", turboTimesheet.getEmployee().getEmployee_id());
        q1.setParameter("months", turboTimesheet.getMonth());
        q1.setParameter("year", String.valueOf(turboTimesheet.getYear()));
        
        if(q1.getResultList().size() > 0) {
        	 return "turbotimesheets/createMessage";
        }
        
        em = MonthsInYear.entityManager();
        TypedQuery<MonthsInYear> q = em.createQuery("select m from MonthsInYear as m where m.year = :year " +
        		"and m.months = :months", MonthsInYear.class);
        q.setParameter("year", String.valueOf(turboTimesheet.getYear()));
        q.setParameter("months", turboTimesheet.getMonth());
        
        if(q.getResultList().size() == 0) {
       	 return "turbotimesheets/createMessage2";

        }
        
        MonthsInYear monthsInYear = q.getResultList().get(0);
        Date toDate = monthsInYear.getTo_date(); 
        Date fromDate = monthsInYear.getFrom_date();
        
            //int MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

            //int daysinMonth = (int)((monthsInYear.getTo_date().getTime() - monthsInYear.getFrom_date().getTime() ) / MILLSECS_PER_DAY + 1);

        	
    		//DateFormat dateFormat =  new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");		
    		
    		Calendar cal1 = Calendar.getInstance();
            cal1.setTime(fromDate); 

    		Calendar cal2 = Calendar.getInstance();
            cal2.setTime(toDate);

                    
    		for (Calendar c = cal1; c.compareTo(cal2) <= 0; c.add(Calendar.DATE, 1)) {

    			Timesheet timesheet = new Timesheet();

    			timesheet.setDate(c.getTime());
    			timesheet.setEmployee(turboTimesheet.getEmployee());
    			timesheet.setReporting_code(ReportingCode.P);
    			timesheet.setOvertime_hours(0);
    			timesheet.setWorking_hours(Constants.IDEAL_HRS);

    			
    			timesheet.persist();

    		}
        	     
        
        return "redirect:/turbotimesheets/" + encodeUrlPathSegment(turboTimesheet.getId().toString(), httpServletRequest);
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
