package com.kreativ.kerp.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.Mst_job_status;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "mst_job_statuses", formBackingObject = Mst_job_status.class)
@RequestMapping("/mst_job_statuses")
@Controller
public class Mst_job_statusController {

	public Mst_job_statusController() {

		/*Mst_job_status mst_job_status = new Mst_job_status("DUMMY");
		if (mst_job_status.findMst_job_statusesByJob_statusEquals("DUMMY").getResultList().size() == 0)
			mst_job_status.persist();*/
	}
	
    @RequestMapping(params = "find=ByJob_statusEquals", method = RequestMethod.GET)
    public String findMst_job_statusesByJob_statusEquals(@RequestParam("job_status") String job_status, Model uiModel) {
        uiModel.addAttribute("mst_job_statuses", Mst_job_status.findMst_job_statusesByJob_statusEquals(job_status).getResultList());
        return "mst_job_statuses/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Mst_job_status mst_job_status, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("mst_job_status", mst_job_status);
            return "mst_job_statuses/create";
        }
        //uiModel.asMap().clear();
        if (mst_job_status.findMst_job_statusesByJob_statusEquals(mst_job_status.getJob_status()).getResultList().size() == 0) {
        mst_job_status.persist();
        return "redirect:/mst_job_statuses/" + encodeUrlPathSegment(mst_job_status.getId().toString(), httpServletRequest);
        } else {
        	return "mst_job_statuses/createMessage";
        }
    }
    
}
