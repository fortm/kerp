package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.ESICTable;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "esictables", formBackingObject = ESICTable.class)
@RequestMapping("/esictables")
@Controller
public class ESICTableController {
}
