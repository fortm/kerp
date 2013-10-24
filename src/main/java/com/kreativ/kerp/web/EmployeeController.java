package com.kreativ.kerp.web;

import java.io.UnsupportedEncodingException;
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
import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.hist.EmployeeHist;
import com.kreativ.kerp.reference.Gender;
import com.kreativ.kerp.reference.JobRole;
import com.kreativ.kerp.reference.JobStatus;
import com.kreativ.kerp.reference.Status;
import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

@RooWebScaffold(path = "employees", formBackingObject = Employee.class, delete = false)
@RequestMapping("/employees")
@Controller
public class EmployeeController {

    @Autowired
    private transient MailSender mailTemplate;
    
    private boolean userInput = false; 
    
    public boolean agetuserInput() {
    	return this.userInput;
    }

    @Autowired
    private transient SimpleMailMessage simpleMailMessage;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Employee employee, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("employee", employee);
            addDateTimeFormatPatterns(uiModel);
            return "employees/create";
        }
        uiModel.asMap().clear();

        //CREATE HISTORY IF JOINING MONTH < CURRENT MONTH 
        
		Calendar cal = Calendar.getInstance();
		cal.setTime(employee.getJoining_date());
		
		int join_month = cal.get(Calendar.MONTH);
		int join_year = cal.get(Calendar.YEAR);
		int join_day = cal.get(Calendar.DATE);
		
        
		Calendar cal1 = Calendar.getInstance();
		int cur_month = cal1.get(Calendar.MONTH);
		
		if(join_month < cur_month) {
			
			userInput = true;
		}

        employee.persist();
        
        
        
        EmployeeHist hist = new EmployeeHist(employee);
        hist.persist();
		//sendMessage("xxx@gmail.com","A new Employee with id " + employee.getEmployee_id()+ " and name " + employee.getFirst_name() + " "+ employee.getLast_name() + " has been created.");
        
		if (!userInput) {
			return "redirect:/employees/"
					+ encodeUrlPathSegment(
							employee.getEmployee_id().toString(),
							httpServletRequest);
		} else {
			return "employees/createMessage1";
		}
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Employee employee, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("employee", employee);
            addDateTimeFormatPatterns(uiModel);
            return "employees/update";
        }
        uiModel.asMap().clear();
        employee.merge();
        EmployeeHist hist = new EmployeeHist(employee);
        hist.persist();
        return "redirect:/employees/" + encodeUrlPathSegment(employee.getEmployee_id().toString(), httpServletRequest);
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("employee_joining_date_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
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

    public void sendMessage(String mailTo, String message) {
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(message);
        mailTemplate.send(simpleMailMessage);
    }
}
