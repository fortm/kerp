package com.kreativ.kerp.time;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.reference.ReportingCode;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findTimesheetsByEmployee", "findTimesheetsByEmployeeAndDateEquals" })
public class Timesheet {

    @NotNull
    @ManyToOne
    private Employee employee;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date date;

    @NotNull
    @Enumerated
    private ReportingCode reporting_code;

    @NotNull
    private int working_hours;

    @NotNull
    private int overtime_hours;
}
