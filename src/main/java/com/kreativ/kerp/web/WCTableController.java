package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.WCTable;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "wctables", formBackingObject = WCTable.class)
@RequestMapping("/wctables")
@Controller
public class WCTableController {
}
