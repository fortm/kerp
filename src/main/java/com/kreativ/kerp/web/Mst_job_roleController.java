package com.kreativ.kerp.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kreativ.kerp.reference.Mst_job_role;

@RooWebScaffold(path = "mst_job_roles", formBackingObject = Mst_job_role.class)
@RequestMapping("/mst_job_roles")
@Controller
public class Mst_job_roleController {
	
	public Mst_job_roleController() {
		
		/*Mst_job_role mst_job_role = new Mst_job_role("DUMMY");
		if(Mst_job_role.findMst_job_rolesByJob_roleEquals("DUMMY") == null) 
		mst_job_role.persist();*/
	}
	
    @RequestMapping(params = "find=ByJob_roleEquals", method = RequestMethod.GET)
    public String findMst_job_rolesByJob_roleEquals(@RequestParam("job_role") String job_role, Model uiModel) {
        uiModel.addAttribute("mst_job_roles", Mst_job_role.findMst_job_rolesByJob_roleEquals(job_role));
        return "mst_job_roles/list";
    }
    
    @RequestMapping(method = RequestMethod.POST)
	public  String create(@Valid Mst_job_role mst_job_role,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("mst_job_role", mst_job_role);
			return "mst_job_roles/create";
		}
		uiModel.asMap().clear();
		/*if (Mst_job_role.findMst_job_rolesByJob_roleEquals(mst_job_role.getJob_role()) == null) {
			mst_job_role.persist();
			return "redirect:/mst_job_roles/"+ encodeUrlPathSegment(mst_job_role.getId().toString(),httpServletRequest);
		} else
			return "mst_job_roles/createMessage";
		*/
		mst_job_role.persist();
		return "redirect:/mst_job_roles/"+ encodeUrlPathSegment(mst_job_role.getId().toString(),httpServletRequest);
	}

}
