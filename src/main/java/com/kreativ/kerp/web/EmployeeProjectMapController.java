package com.kreativ.kerp.web;

import java.io.UnsupportedEncodingException;

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

import com.kreativ.kerp.domain.EmployeeProjectMap;
import com.kreativ.kerp.domain.hist.EmployeeProjectMapHist;

@RooWebScaffold(path = "employeeprojectmaps", formBackingObject = EmployeeProjectMap.class)
@RequestMapping("/employeeprojectmaps")
@Controller
public class EmployeeProjectMapController {
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid EmployeeProjectMap employeeProjectMap, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("employeeProjectMap", employeeProjectMap);
            return "employeeprojectmaps/create";
        }
        
        if( EmployeeProjectMap.findEmployeeProjectMapsByEmployee(employeeProjectMap.getEmployee()).getResultList().size() > 0 ) {
        	
        	return "employeeprojectmaps/create";
        	
        }
        
        uiModel.asMap().clear();
        employeeProjectMap.persist();
        
        EmployeeProjectMapHist empProjMapHist = new EmployeeProjectMapHist(employeeProjectMap);
        empProjMapHist.persist();
                
        
        return "redirect:/employeeprojectmaps/" + encodeUrlPathSegment(employeeProjectMap.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid EmployeeProjectMap employeeProjectMap, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("employeeProjectMap", employeeProjectMap);
            return "employeeprojectmaps/update";
        }
        uiModel.asMap().clear();
        employeeProjectMap.merge();
        
        
        EmployeeProjectMapHist empProjMapHist = new EmployeeProjectMapHist(employeeProjectMap);
        empProjMapHist.persist();
        
        return "redirect:/employeeprojectmaps/" + encodeUrlPathSegment(employeeProjectMap.getId().toString(), httpServletRequest);
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
	
}
