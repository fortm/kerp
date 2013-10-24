// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.reference.Gender;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.Mst_job_status;
import com.kreativ.kerp.reference.PaymentMode;
import com.kreativ.kerp.reference.Status;
import com.kreativ.kerp.reference.WorkArea;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect EmployeeController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String EmployeeController.createForm(Model uiModel) {
        uiModel.addAttribute("employee", new Employee());
        addDateTimeFormatPatterns(uiModel);
        List dependencies = new ArrayList();
        if (Project.countProjects() == 0) {
            dependencies.add(new String[]{"project", "projects"});
        }
        if (Mst_job_role.countMst_job_roles() == 0) {
            dependencies.add(new String[]{"mst_job_role", "mst_job_roles"});
        }
        if (Mst_job_status.countMst_job_statuses() == 0) {
            dependencies.add(new String[]{"mst_job_status", "mst_job_statuses"});
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "employees/create";
    }
    
    @RequestMapping(value = "/{employee_id}", method = RequestMethod.GET)
    public String EmployeeController.show(@PathVariable("employee_id") Integer employee_id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("employee", Employee.findEmployee(employee_id));
        uiModel.addAttribute("itemId", employee_id);
        return "employees/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String EmployeeController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("employees", Employee.findEmployeeEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Employee.countEmployees() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("employees", Employee.findAllEmployees());
        }
        addDateTimeFormatPatterns(uiModel);
        return "employees/list";
    }
    
    @RequestMapping(value = "/{employee_id}", params = "form", method = RequestMethod.GET)
    public String EmployeeController.updateForm(@PathVariable("employee_id") Integer employee_id, Model uiModel) {
        uiModel.addAttribute("employee", Employee.findEmployee(employee_id));
        addDateTimeFormatPatterns(uiModel);
        return "employees/update";
    }
    
    @ModelAttribute("employees")
    public Collection<Employee> EmployeeController.populateEmployees() {
        return Employee.findAllEmployees();
    }
    
    @ModelAttribute("projects")
    public Collection<Project> EmployeeController.populateProjects() {
        return Project.findAllProjects();
    }
    
    @ModelAttribute("genders")
    public Collection<Gender> EmployeeController.populateGenders() {
        return Arrays.asList(Gender.class.getEnumConstants());
    }
    
    @ModelAttribute("mst_job_roles")
    public Collection<Mst_job_role> EmployeeController.populateMst_job_roles() {
        return Mst_job_role.findAllMst_job_roles();
    }
    
    @ModelAttribute("mst_job_statuses")
    public Collection<Mst_job_status> EmployeeController.populateMst_job_statuses() {
        return Mst_job_status.findAllMst_job_statuses();
    }
    
    @ModelAttribute("paymentmodes")
    public Collection<PaymentMode> EmployeeController.populatePaymentModes() {
        return Arrays.asList(PaymentMode.class.getEnumConstants());
    }
    
    @ModelAttribute("statuses")
    public Collection<Status> EmployeeController.populateStatuses() {
        return Arrays.asList(Status.class.getEnumConstants());
    }
    
    @ModelAttribute("workareas")
    public Collection<WorkArea> EmployeeController.populateWorkAreas() {
        return Arrays.asList(WorkArea.class.getEnumConstants());
    }
    
}
