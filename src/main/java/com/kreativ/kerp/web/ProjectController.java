package com.kreativ.kerp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;

@RooWebScaffold(path = "projects", formBackingObject = Project.class, delete = false)
@RequestMapping("/projects")
@Controller
public class ProjectController {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Project project, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("project", project);
			return "projects/create";
		}
		uiModel.asMap().clear();

		project.persist();

		return "redirect:/projects/"
				+ encodeUrlPathSegment(project.getProject_id().toString(),
						httpServletRequest);
	}

}
