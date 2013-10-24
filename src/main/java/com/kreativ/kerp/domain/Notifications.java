package com.kreativ.kerp.domain;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.kreativ.kerp.reference.completed;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findNotificationsesByEmployee" })
public class Notifications {

    @ManyToOne
    private Employee employee;

    @Enumerated
    private completed notified;
}
