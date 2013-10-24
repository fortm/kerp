// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.TurboTimesheet;
import com.kreativ.kerp.reference.Months;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect TurboTimesheetController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String TurboTimesheetController.createForm(Model uiModel) {
        uiModel.addAttribute("turboTimesheet", new TurboTimesheet());
        return "turbotimesheets/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String TurboTimesheetController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("turbotimesheet", TurboTimesheet.findTurboTimesheet(id));
        uiModel.addAttribute("itemId", id);
        return "turbotimesheets/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String TurboTimesheetController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("turbotimesheets", TurboTimesheet.findTurboTimesheetEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) TurboTimesheet.countTurboTimesheets() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("turbotimesheets", TurboTimesheet.findAllTurboTimesheets());
        }
        return "turbotimesheets/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String TurboTimesheetController.update(@Valid TurboTimesheet turboTimesheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("turboTimesheet", turboTimesheet);
            return "turbotimesheets/update";
        }
        uiModel.asMap().clear();
        turboTimesheet.merge();
        return "redirect:/turbotimesheets/" + encodeUrlPathSegment(turboTimesheet.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String TurboTimesheetController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("turboTimesheet", TurboTimesheet.findTurboTimesheet(id));
        return "turbotimesheets/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String TurboTimesheetController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TurboTimesheet.findTurboTimesheet(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/turbotimesheets";
    }
    
    @ModelAttribute("employees")
    public Collection<Employee> TurboTimesheetController.populateEmployees() {
        return Employee.findAllEmployees();
    }
    
    @ModelAttribute("turbotimesheets")
    public Collection<TurboTimesheet> TurboTimesheetController.populateTurboTimesheets() {
        return TurboTimesheet.findAllTurboTimesheets();
    }
    
    @ModelAttribute("monthses")
    public Collection<Months> TurboTimesheetController.populateMonthses() {
        return Arrays.asList(Months.class.getEnumConstants());
    }
    
}