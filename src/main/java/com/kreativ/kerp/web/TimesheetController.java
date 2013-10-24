package com.kreativ.kerp.web;

import java.util.Calendar;

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

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.MonthsInYear;
import com.kreativ.kerp.reference.Constants;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.ReportingCode;
import com.kreativ.kerp.time.Timesheet;

@RooWebScaffold(path = "timesheets", formBackingObject = Timesheet.class)
@RequestMapping("/timesheets")
@Controller
public class TimesheetController {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Timesheet timesheet,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("timesheet", timesheet);
			addDateTimeFormatPatterns(uiModel);
			return "timesheets/create";
		}
		// uiModel.asMap().clear();

		// Check if already exists
		Employee emp = timesheet.getEmployee();
		if (Timesheet
				.findTimesheetsByEmployeeAndDateEquals(emp, timesheet.getDate())
				.getResultList().size() > 0) {
			return "timesheets/createMessage";
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(timesheet.getDate());
		int hist_month = cal.get(Calendar.MONTH);
		int hist_year = cal.get(Calendar.YEAR);
		int hist_day = cal.get(Calendar.DATE);

		int day = cal.get(Calendar.DAY_OF_WEEK);
		

		if (day == 1 || day == 7) {
			timesheet.setOvertime_hours(0);
			timesheet.setWorking_hours(Constants.IDEAL_HRS);
			timesheet.setReporting_code(ReportingCode.P);						
		}
		else
		// Set Hours and overtime to zero if Reporting code is Absent
		if (timesheet.getReporting_code().equals(ReportingCode.A)) {
			timesheet.setOvertime_hours(0);
			timesheet.setWorking_hours(0);
		} else {
			if (timesheet.getOvertime_hours() == 0
					&& timesheet.getWorking_hours() == 0) {
				timesheet.setOvertime_hours(0);
				timesheet.setWorking_hours(Constants.IDEAL_HRS);
			}
		}

		
		
		timesheet.persist();
		return "redirect:/timesheets/"
				+ encodeUrlPathSegment(timesheet.getId().toString(),
						httpServletRequest);

	}
	
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Timesheet timesheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("timesheet", timesheet);
            addDateTimeFormatPatterns(uiModel);
            return "timesheets/update";
        }
        
        
		Employee emp = timesheet.getEmployee();

		Calendar cal = Calendar.getInstance();
		cal.setTime(timesheet.getDate());
		int hist_month = cal.get(Calendar.MONTH);
		int hist_year = cal.get(Calendar.YEAR);
		int hist_day = cal.get(Calendar.DATE);

		int day = cal.get(Calendar.DAY_OF_WEEK);
		

		if (day == 1 || day == 7) {
			timesheet.setOvertime_hours(0);
			timesheet.setWorking_hours(Constants.IDEAL_HRS);
			timesheet.setReporting_code(ReportingCode.P);						
		}
		else
		// Set Hours and overtime to zero if Reporting code is Absent
		if (timesheet.getReporting_code().equals(ReportingCode.A)) {
			timesheet.setOvertime_hours(0);
			timesheet.setWorking_hours(0);
		} else {
			if (timesheet.getOvertime_hours() == 0
					&& timesheet.getWorking_hours() == 0) {
				timesheet.setOvertime_hours(0);
				timesheet.setWorking_hours(Constants.IDEAL_HRS);
			}
		}

		
        
        
        uiModel.asMap().clear();
        timesheet.merge();
        return "redirect:/timesheets/" + encodeUrlPathSegment(timesheet.getId().toString(), httpServletRequest);
    }
    

}
