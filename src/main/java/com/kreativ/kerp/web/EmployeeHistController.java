package com.kreativ.kerp.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.kreativ.kerp.domain.hist.EmployeeHist;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "employeehists", formBackingObject = EmployeeHist.class, delete = false, update = false)
@RequestMapping("/employeehists")
@Controller
public class EmployeeHistController {
}
