// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.ProfTaxTable;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ProfTaxController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String ProfTaxController.create(@Valid ProfTaxTable profTaxTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("profTaxTable", profTaxTable);
            return "proftaxtables/create";
        }
        uiModel.asMap().clear();
        profTaxTable.persist();
        return "redirect:/proftaxtables/" + encodeUrlPathSegment(profTaxTable.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ProfTaxController.createForm(Model uiModel) {
        uiModel.addAttribute("profTaxTable", new ProfTaxTable());
        return "proftaxtables/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ProfTaxController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("proftaxtable", ProfTaxTable.findProfTaxTable(id));
        uiModel.addAttribute("itemId", id);
        return "proftaxtables/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ProfTaxController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("proftaxtables", ProfTaxTable.findProfTaxTableEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) ProfTaxTable.countProfTaxTables() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("proftaxtables", ProfTaxTable.findAllProfTaxTables());
        }
        return "proftaxtables/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ProfTaxController.update(@Valid ProfTaxTable profTaxTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("profTaxTable", profTaxTable);
            return "proftaxtables/update";
        }
        uiModel.asMap().clear();
        profTaxTable.merge();
        return "redirect:/proftaxtables/" + encodeUrlPathSegment(profTaxTable.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ProfTaxController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("profTaxTable", ProfTaxTable.findProfTaxTable(id));
        return "proftaxtables/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ProfTaxController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ProfTaxTable.findProfTaxTable(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/proftaxtables";
    }
    
    @ModelAttribute("proftaxtables")
    public Collection<ProfTaxTable> ProfTaxController.populateProfTaxTables() {
        return ProfTaxTable.findAllProfTaxTables();
    }
    
    String ProfTaxController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}