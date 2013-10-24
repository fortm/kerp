// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.payment.ESICTable;
import com.kreativ.kerp.reference.TYPE;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
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

privileged aspect ESICTableController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String ESICTableController.create(@Valid ESICTable ESICTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("ESICTable", ESICTable);
            return "esictables/create";
        }
        uiModel.asMap().clear();
        ESICTable.persist();
        return "redirect:/esictables/" + encodeUrlPathSegment(ESICTable.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ESICTableController.createForm(Model uiModel) {
        uiModel.addAttribute("ESICTable", new ESICTable());
        return "esictables/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ESICTableController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("esictable", ESICTable.findESICTable(id));
        uiModel.addAttribute("itemId", id);
        return "esictables/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ESICTableController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("esictables", ESICTable.findESICTableEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) ESICTable.countESICTables() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("esictables", ESICTable.findAllESICTables());
        }
        return "esictables/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ESICTableController.update(@Valid ESICTable ESICTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("ESICTable", ESICTable);
            return "esictables/update";
        }
        uiModel.asMap().clear();
        ESICTable.merge();
        return "redirect:/esictables/" + encodeUrlPathSegment(ESICTable.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ESICTableController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("ESICTable", ESICTable.findESICTable(id));
        return "esictables/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ESICTableController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ESICTable.findESICTable(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/esictables";
    }
    
    @ModelAttribute("esictables")
    public Collection<ESICTable> ESICTableController.populateESICTables() {
        return ESICTable.findAllESICTables();
    }
    
    @ModelAttribute("types")
    public Collection<TYPE> ESICTableController.populateTYPES() {
        return Arrays.asList(TYPE.class.getEnumConstants());
    }
    
    String ESICTableController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
