package com.kreativ.kerp.web;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.kreativ.kerp.domain.MonthsInYear;
import com.kreativ.kerp.reference.Months;

@RooWebScaffold(path = "monthsinyears", formBackingObject = MonthsInYear.class)
@RequestMapping("/monthsinyears")
@Controller
public class MonthsInYearController {
	

    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid MonthsInYear monthsInYear, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
    	
    	if (bindingResult.hasErrors()) {
    		
            uiModel.addAttribute("monthsInYear", monthsInYear);
            addDateTimeFormatPatterns(uiModel);
            return "monthsinyears/create";
        }

        MonthsInYear monthsInYear1 = null;
       
    	try{
    		
		//DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

		Date date1 = monthsInYear.getFrom_salary_month();
		Date date2 = monthsInYear.getTo_salary_month();
		
		DateFormat dateFormat =  new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");		
	
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dateFormat.parse(date1.toString())); 

		Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dateFormat.parse(date2.toString()));

                
		for (Calendar c = cal1; c.compareTo(cal2) <= 0; c.add(Calendar.MONTH, 1)) {

			monthsInYear1 = new MonthsInYear();
			monthsInYear1.setFrom_salary_month(monthsInYear.getFrom_salary_month());
			monthsInYear1.setTo_salary_month(monthsInYear.getTo_salary_month());
			
			c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));
			monthsInYear1.setFrom_date(c.getTime());
                		
            Calendar cal = Calendar.getInstance();
            cal.setTime(c.getTime());
            int hist_month = cal.get(Calendar.MONTH);
            int hist_year = cal.get(Calendar.YEAR);
            
            monthsInYear1.setYear(String.valueOf(hist_year));
            monthsInYear1.setMonths(Months.get(hist_month)) ;
                        			
			c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
			monthsInYear1.setTo_date(c.getTime());
			
			monthsInYear1.persist();

		}
		}catch(Exception e ){
			System.out.println("An error happened while date conversion ");
		}
        uiModel.asMap().clear();
        // monthsInYear.persist();
        
        return "redirect:/monthsinyears/" + encodeUrlPathSegment(monthsInYear1.getId().toString(), httpServletRequest);
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
	
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("monthsInYear_from_salary_month_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monthsInYear_to_salary_month_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monthsInYear_from_date_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monthsInYear_to_date_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
}
