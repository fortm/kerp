// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ProjectController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ProjectController.createForm(Model uiModel) {
        uiModel.addAttribute("project", new Project());
        addDateTimeFormatPatterns(uiModel);
        return "projects/create";
    }
    
    @RequestMapping(value = "/{project_id}", method = RequestMethod.GET)
    public String ProjectController.show(@PathVariable("project_id") Integer project_id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("project", Project.findProject(project_id));
        uiModel.addAttribute("itemId", project_id);
        return "projects/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ProjectController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("projects", Project.findProjectEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Project.countProjects() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("projects", Project.findAllProjects());
        }
        addDateTimeFormatPatterns(uiModel);
        return "projects/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ProjectController.update(@Valid Project project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("project", project);
            addDateTimeFormatPatterns(uiModel);
            return "projects/update";
        }
        uiModel.asMap().clear();
        project.merge();
        return "redirect:/projects/" + encodeUrlPathSegment(project.getProject_id().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{project_id}", params = "form", method = RequestMethod.GET)
    public String ProjectController.updateForm(@PathVariable("project_id") Integer project_id, Model uiModel) {
        uiModel.addAttribute("project", Project.findProject(project_id));
        addDateTimeFormatPatterns(uiModel);
        return "projects/update";
    }
    
    @ModelAttribute("employees")
    public Collection<Employee> ProjectController.populateEmployees() {
        return Employee.findAllEmployees();
    }
    
    @ModelAttribute("projects")
    public Collection<Project> ProjectController.populateProjects() {
        return Project.findAllProjects();
    }
    
    void ProjectController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("project_start_date_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    String ProjectController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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