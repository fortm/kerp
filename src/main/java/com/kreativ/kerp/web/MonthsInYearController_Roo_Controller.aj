// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.MonthsInYear;
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

privileged aspect MonthsInYearController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String MonthsInYearController.createForm(Model uiModel) {
        uiModel.addAttribute("monthsInYear", new MonthsInYear());
        addDateTimeFormatPatterns(uiModel);
        return "monthsinyears/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String MonthsInYearController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("monthsinyear", MonthsInYear.findMonthsInYear(id));
        uiModel.addAttribute("itemId", id);
        return "monthsinyears/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String MonthsInYearController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("monthsinyears", MonthsInYear.findMonthsInYearEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) MonthsInYear.countMonthsInYears() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("monthsinyears", MonthsInYear.findAllMonthsInYears());
        }
        addDateTimeFormatPatterns(uiModel);
        return "monthsinyears/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String MonthsInYearController.update(@Valid MonthsInYear monthsInYear, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("monthsInYear", monthsInYear);
            addDateTimeFormatPatterns(uiModel);
            return "monthsinyears/update";
        }
        uiModel.asMap().clear();
        monthsInYear.merge();
        return "redirect:/monthsinyears/" + encodeUrlPathSegment(monthsInYear.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String MonthsInYearController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("monthsInYear", MonthsInYear.findMonthsInYear(id));
        addDateTimeFormatPatterns(uiModel);
        return "monthsinyears/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String MonthsInYearController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        MonthsInYear.findMonthsInYear(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/monthsinyears";
    }
    
    @ModelAttribute("monthsinyears")
    public Collection<MonthsInYear> MonthsInYearController.populateMonthsInYears() {
        return MonthsInYear.findAllMonthsInYears();
    }
    
    @ModelAttribute("monthses")
    public Collection<Months> MonthsInYearController.populateMonthses() {
        return Arrays.asList(Months.class.getEnumConstants());
    }
    
}
