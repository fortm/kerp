// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.PFTable;
import com.kreativ.kerp.reference.TYPE;
import java.lang.String;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect PFTableController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByTypeEquals", "form" }, method = RequestMethod.GET)
    public String PFTableController.findPFTablesByTypeEqualsForm(Model uiModel) {
        uiModel.addAttribute("types", java.util.Arrays.asList(TYPE.class.getEnumConstants()));
        return "pftables/findPFTablesByTypeEquals";
    }
    
    @RequestMapping(params = "find=ByTypeEquals", method = RequestMethod.GET)
    public String PFTableController.findPFTablesByTypeEquals(@RequestParam("type") TYPE type, Model uiModel) {
        uiModel.addAttribute("pftables", PFTable.findPFTablesByTypeEquals(type).getResultList());
        return "pftables/list";
    }
    
}
