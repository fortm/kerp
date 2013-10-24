// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Notifications;
import com.kreativ.kerp.reference.completed;
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

privileged aspect NotificationController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String NotificationController.create(@Valid Notifications notifications, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("notifications", notifications);
            return "notificationses/create";
        }
        uiModel.asMap().clear();
        notifications.persist();
        return "redirect:/notificationses/" + encodeUrlPathSegment(notifications.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String NotificationController.createForm(Model uiModel) {
        uiModel.addAttribute("notifications", new Notifications());
        return "notificationses/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String NotificationController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("notifications", Notifications.findNotifications(id));
        uiModel.addAttribute("itemId", id);
        return "notificationses/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String NotificationController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("notificationses", Notifications.findNotificationsEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Notifications.countNotificationses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("notificationses", Notifications.findAllNotificationses());
        }
        return "notificationses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String NotificationController.update(@Valid Notifications notifications, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("notifications", notifications);
            return "notificationses/update";
        }
        uiModel.asMap().clear();
        notifications.merge();
        return "redirect:/notificationses/" + encodeUrlPathSegment(notifications.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String NotificationController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("notifications", Notifications.findNotifications(id));
        return "notificationses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String NotificationController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Notifications.findNotifications(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/notificationses";
    }
    
    @ModelAttribute("employees")
    public Collection<Employee> NotificationController.populateEmployees() {
        return Employee.findAllEmployees();
    }
    
    @ModelAttribute("notificationses")
    public Collection<Notifications> NotificationController.populateNotificationses() {
        return Notifications.findAllNotificationses();
    }
    
    @ModelAttribute("completeds")
    public Collection<completed> NotificationController.populatecompleteds() {
        return Arrays.asList(completed.class.getEnumConstants());
    }
    
    String NotificationController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
