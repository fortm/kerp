package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.PFTable;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "pftables", formBackingObject = PFTable.class)
@RequestMapping("/pftables")
@Controller
public class PFTableController {
}
