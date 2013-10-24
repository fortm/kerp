package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.hist.EmployeeProjectMapHist;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "employeeprojectmaphists", formBackingObject = EmployeeProjectMapHist.class)
@RequestMapping("/employeeprojectmaphists")
@Controller
public class EmployeeProjectMapHistController {
}
