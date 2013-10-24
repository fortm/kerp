package com.kreativ.kerp.domain.reports;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeSalaryData;
import com.kreativ.kerp.reference.Months;

@RooJavaBean
@RooToString
@RooEntity
public class ConsolidatedEmpSalReport {

    public ConsolidatedEmpSalReport(EmployeeSalaryData empSalaryData) {
        super();
        this.employee = empSalaryData.getEmployee();
        this.year = empSalaryData.getYear();
        this.month = empSalaryData.getMonth();
        this.project = empSalaryData.getProject();
        this.role = empSalaryData.getRole();
        Attendance = empSalaryData.getAttendance();
        this.basic = empSalaryData.getBasic();
        this.da = empSalaryData.getDa();
        this.special_allowance = empSalaryData.getSpecial_allowance();
        this.washing_allowance = empSalaryData.getWashing_allowance();
        this.medical_allowance = empSalaryData.getMedical_allowance();
        this.educational_allowance = empSalaryData.getEducational_allowance();
        this.conveyance_allowance = empSalaryData.getConveyance_allowance();
        this.other_allowance = empSalaryData.getOther_allowance();
        this.ot_rate = empSalaryData.getOt();
        this.ex_gratia = empSalaryData.getEx_gratia();
        this.hra_percentage = empSalaryData.getHra_percentage();
        this.service_charges = empSalaryData.getService_charges();
        this.bonus_flag = empSalaryData.isBonus_flag();
        this.canteen_allowance = empSalaryData.getCanteen_allowance();
        this.ot_days = empSalaryData.getOt_days();
        this.ot = empSalaryData.getOt();
        this.gross_salary = empSalaryData.getGross_salary();
        this.hra = empSalaryData.getHra();
        this.gross_Earnings = empSalaryData.getGross_Earnings();
        WC = empSalaryData.getWC();
        this.pf = empSalaryData.getPf();
        this.esic = empSalaryData.getEsic();
        this.professional_tax = empSalaryData.getProfessional_tax();
        LWF = empSalaryData.getLWF();
        this.sd = empSalaryData.getSd();
        this.atm = empSalaryData.getAtm();
        this.total_deductions = empSalaryData.getTotal_deductions();
        this.net_payment = empSalaryData.getNet_payment();
    }

    @ManyToOne
    private Employee employee;

    @NotNull
    private String year;

    @NotNull
    @Enumerated
    private Months month;

    private String project;

    private String role;

    private int Attendance;

    private float basic;

    private float da;

    private float special_allowance;

    private float washing_allowance;

    private float medical_allowance;

    private float educational_allowance;

    private float conveyance_allowance;

    private float other_allowance;

    private float ot_rate;

    private float ex_gratia;

    private float hra_percentage;

    private float service_charges;

    private boolean bonus_flag;

    private float canteen_allowance;

    private float ot_days;

    private float ot;

    private float gross_salary;

    private float hra;

    private float gross_Earnings;

    private float WC;

    private float pf;

    private float esic;

    private float professional_tax;

    private float LWF;

    private float sd;

    private float atm;

    private float total_deductions;

    private float net_payment;
}
