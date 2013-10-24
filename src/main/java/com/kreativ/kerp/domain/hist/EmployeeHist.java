package com.kreativ.kerp.domain.hist;

import java.util.Calendar;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import addon.roo.audittimestamp.RooAuditTimeStamp;
import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.reference.Months;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.WorkArea;

@RooJavaBean
@RooToString
@RooAuditTimeStamp
@RooEntity(finders = { "findEmployeeHistsByEmployee" })
public class EmployeeHist {

    public EmployeeHist(Employee employee) {
        super();
        this.employee = employee;
        this.workArea = employee.getWorkArea();
        this.project = employee.getProject();
        this.job_role = employee.getMst_job_role();
        Calendar cal = Calendar.getInstance();
        int cur_month = cal.get(Calendar.MONTH);
        int cur_year = cal.get(Calendar.YEAR);
        this.year = cur_year;
        this.month = Months.get(cur_month);
    }

    @NotNull
    @ManyToOne
    private Employee employee;

    @NotNull
    private int year;

    @NotNull
    private Months month;

    @NotNull
    @Enumerated
    private WorkArea workArea;

    @NotNull
    @ManyToOne
    private Project project;

    @NotNull
    @ManyToOne
    private Mst_job_role job_role;

    public static String findMst_job_roleBy_Id(EmployeeHist employeeHist) {
        if (employeeHist == null) throw new IllegalArgumentException("The employee argument is required");
        String jobRole = Mst_job_role.findMst_job_rolesByJob_IdEquals(employeeHist.getJob_role().getId());
        return jobRole;
    }
}
