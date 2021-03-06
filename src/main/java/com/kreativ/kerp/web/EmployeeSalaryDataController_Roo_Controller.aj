// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeSalaryData;
import com.kreativ.kerp.reference.Months;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect EmployeeSalaryDataController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String EmployeeSalaryDataController.createForm(Model uiModel) {
        uiModel.addAttribute("employeeSalaryData", new EmployeeSalaryData());
        return "employeesalarydatas/create";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String EmployeeSalaryDataController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("employeesalarydatas", EmployeeSalaryData.findEmployeeSalaryDataEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) EmployeeSalaryData.countEmployeeSalaryDatas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("employeesalarydatas", EmployeeSalaryData.findAllEmployeeSalaryDatas());
        }
        return "employeesalarydatas/list";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String EmployeeSalaryDataController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EmployeeSalaryData.findEmployeeSalaryData(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/employeesalarydatas";
    }
    
    @ModelAttribute("employees")
    public Collection<Employee> EmployeeSalaryDataController.populateEmployees() {
        return Employee.findAllEmployees();
    }
    
    @ModelAttribute("employeesalarydatas")
    public Collection<EmployeeSalaryData> EmployeeSalaryDataController.populateEmployeeSalaryDatas() {
        return EmployeeSalaryData.findAllEmployeeSalaryDatas();
    }
    
    @ModelAttribute("monthses")
    public Collection<Months> EmployeeSalaryDataController.populateMonthses() {
        return Arrays.asList(Months.class.getEnumConstants());
    }
    
}
