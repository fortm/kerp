package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Notifications;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "notificationses", formBackingObject = Notifications.class)
@RequestMapping("/notificationses")
@Controller
public class NotificationController {
}
