package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeBankDetails;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RooWebScaffold(path = "employeebankdetailses", formBackingObject = EmployeeBankDetails.class)
@RequestMapping("/employeebankdetailses")
@Controller
public class EmployeeBankDetailsController {
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	EmployeeBankDetails.findEmployeeBankDetails(id).setRole(Employee.findMst_job_roleBy_Id(EmployeeBankDetails.findEmployeeBankDetails(id).getEmployee()));
        uiModel.addAttribute("employeebankdetails", EmployeeBankDetails.findEmployeeBankDetails(id));
        uiModel.addAttribute("itemId", id);
        return "employeebankdetailses/show";
    }
	
}
