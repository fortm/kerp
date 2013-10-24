// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.report.FinancialYearReport;
import com.kreativ.kerp.reference.ReportYearlyOptions;
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

privileged aspect FinancialYearReportController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String FinancialYearReportController.createForm(Model uiModel) {
        uiModel.addAttribute("financialYearReport", new FinancialYearReport());
        return "financialyearreports/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String FinancialYearReportController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("financialyearreport", FinancialYearReport.findFinancialYearReport(id));
        uiModel.addAttribute("itemId", id);
        return "financialyearreports/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String FinancialYearReportController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("financialyearreports", FinancialYearReport.findFinancialYearReportEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) FinancialYearReport.countFinancialYearReports() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("financialyearreports", FinancialYearReport.findAllFinancialYearReports());
        }
        return "financialyearreports/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String FinancialYearReportController.update(@Valid FinancialYearReport financialYearReport, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("financialYearReport", financialYearReport);
            return "financialyearreports/update";
        }
        uiModel.asMap().clear();
        financialYearReport.merge();
        return "redirect:/financialyearreports/" + encodeUrlPathSegment(financialYearReport.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String FinancialYearReportController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("financialYearReport", FinancialYearReport.findFinancialYearReport(id));
        return "financialyearreports/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String FinancialYearReportController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        FinancialYearReport.findFinancialYearReport(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/financialyearreports";
    }
    
    @ModelAttribute("financialyearreports")
    public Collection<FinancialYearReport> FinancialYearReportController.populateFinancialYearReports() {
        return FinancialYearReport.findAllFinancialYearReports();
    }
    
    @ModelAttribute("reportyearlyoptionses")
    public Collection<ReportYearlyOptions> FinancialYearReportController.populateReportYearlyOptionses() {
        return Arrays.asList(ReportYearlyOptions.class.getEnumConstants());
    }
    
}