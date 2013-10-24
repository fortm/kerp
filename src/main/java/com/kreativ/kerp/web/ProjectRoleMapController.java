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

import com.kreativ.kerp.domain.ProjectRoleMap;
import com.kreativ.kerp.domain.hist.ProjectRoleMapHist;

@RooWebScaffold(path = "projectrolemaps", formBackingObject = ProjectRoleMap.class)
@RequestMapping("/projectrolemaps")
@Controller
public class ProjectRoleMapController {
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid ProjectRoleMap projectRoleMap, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("projectRoleMap", projectRoleMap);
            return "projectrolemaps/create";
        }
        
        if(ProjectRoleMap.findProjectRoleMapsByProjectAndRole(projectRoleMap.getProject(),projectRoleMap.getRole()).getResultList().size() > 0) {
        	return "projectrolemaps/create";
        }
        
        uiModel.asMap().clear();
        projectRoleMap.persist();
        
        ProjectRoleMapHist projRoleMapHist = new ProjectRoleMapHist(projectRoleMap);
        projRoleMapHist.persist();
        
        return "redirect:/projectrolemaps/" + encodeUrlPathSegment(projectRoleMap.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid ProjectRoleMap projectRoleMap, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("projectRoleMap", projectRoleMap);
            return "projectrolemaps/update";
        }
        uiModel.asMap().clear();
        projectRoleMap.merge();
        
        ProjectRoleMapHist projRoleMapHist = new ProjectRoleMapHist(projectRoleMap);
        projRoleMapHist.persist();
        
        
        return "redirect:/projectrolemaps/" + encodeUrlPathSegment(projectRoleMap.getId().toString(), httpServletRequest);
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
	
}
