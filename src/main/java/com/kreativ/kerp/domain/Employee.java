package com.kreativ.kerp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import addon.roo.audittimestamp.RooAuditTimeStamp;

import com.kreativ.kerp.reference.Gender;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.Mst_job_status;
import com.kreativ.kerp.reference.PaymentMode;
import com.kreativ.kerp.reference.Status;
import com.kreativ.kerp.reference.WorkArea;

@RooJavaBean
@RooToString
@RooAuditTimeStamp
@RooEntity(finders = { "findEmployeesByEmployee_id", "findEmployeesByMst_job_role" })
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer employee_id;

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    private String user_name;

    private String address_line_1;

    private String address_line_2;

    private String city;

    private String country;
    
    @Enumerated
    private PaymentMode paymentMode;

    @NotNull
    @Enumerated
    private Gender gender;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date joining_date;

    @NotNull
    @Enumerated
    private Status status;

    private String email;
    
    @NotNull
    @Enumerated
    private WorkArea workArea;
    
    @NotNull
    @ManyToOne
    private Project project;

    @NotNull
    @ManyToOne
    private Mst_job_role mst_job_role;
    
    @NotNull
    @ManyToOne
    private Mst_job_status mst_job_status;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFirst_name()).append(" ");
        sb.append(getLast_name());
        return sb.toString();
    }

    
    public static String findMst_job_roleBy_Id(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("The employee argument is required");
        String jobRole = Mst_job_role.findMst_job_rolesByJob_IdEquals(employee.getMst_job_role().getId()); 
        return jobRole;
    }
    
    /*public static String findMst_job_roleBy_Id(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("The employee argument is required");
        EntityManager em = Employee.entityManager();
        System.out.println("Before Employee="+employee.getEmployee_id());
        Query  q = em.createQuery("SELECT JOB_ROLE FROM MST_JOB_ROLE M, EMPLOYEE E where " +
        		"M.ID = E.MST_JOB_ROLE AND E.EMPLOYEE_ID = :employee_id");
        q.setParameter("employee_id", employee.getEmployee_id());
        System.out.println("Employee in Employee="+employee.getEmployee_id()); 
        return q.getResultList().get(0).toString();
    }*/
        
}
