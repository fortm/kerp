package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.ProfTaxTable;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "proftaxtables", formBackingObject = ProfTaxTable.class)
@RequestMapping("/proftaxtables")
@Controller
public class ProfTaxController {
}
