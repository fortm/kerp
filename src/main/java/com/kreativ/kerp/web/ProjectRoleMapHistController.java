package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.hist.ProjectRoleMapHist;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "projectrolemaphists", formBackingObject = ProjectRoleMapHist.class)
@RequestMapping("/projectrolemaphists")
@Controller
public class ProjectRoleMapHistController {
}
