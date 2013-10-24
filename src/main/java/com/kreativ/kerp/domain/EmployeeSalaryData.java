package com.kreativ.kerp.domain;

import java.text.DecimalFormat;
import java.util.HashMap;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.kreativ.kerp.reference.Months;
import ca.digitalface.jasperoo.RooJasperoo;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findEmployeeSalaryDatasById", "findEmployeeSalaryDatasByEmployee", "findEmployeeSalaryDatasByEmployeeAndYearAndMonth" })
public class EmployeeSalaryData {

    @ManyToOne
    private Employee employee;

    @NotNull
    private String year;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Months month;

    public float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }

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

    public void asetGross_salary() {
        this.gross_salary = roundTwoDecimals(basic + da + special_allowance);
    }

    public void asetOt(int ot_days) {
        this.ot = roundTwoDecimals(ot_rate * ot_days);
    }

    public void asetOt(float otDays) {
        this.ot = roundTwoDecimals(ot_rate * otDays);
    }

    private float hra;

    public void asetHra() {
        this.hra = roundTwoDecimals((basic + da) * hra_percentage / 100);
    }

    private float gross_Earnings;

    public void asetGross_Earnings() {
        this.gross_Earnings = roundTwoDecimals(gross_salary + hra + washing_allowance + medical_allowance + educational_allowance + +conveyance_allowance + canteen_allowance + other_allowance + ot + ex_gratia);
    }

    private float WC;

    public void asetWC(float wc_percentage) {
        this.WC = roundTwoDecimals(WC * wc_percentage / 100);
    }

    private float pf;

    public void asetPf(float pfPercentage) {
        this.pf = roundTwoDecimals(gross_salary * pfPercentage / 100);
    }

    private float esic;

    public void asetEsic(float esicPercentage) {
        this.esic = roundTwoDecimals( (gross_Earnings - washing_allowance)* esicPercentage / 100);
    }

    private float professional_tax;

    public void asetProfessional_tax(float professional_tax) {
        this.professional_tax = roundTwoDecimals(professional_tax);
    }

    private float LWF;

    private float sd;

    private float atm;

    private float total_deductions;

    public void asetTotal_deductions() {
        this.total_deductions = roundTwoDecimals(pf + esic + LWF + professional_tax + sd + atm);
    }

    private float net_payment;

    public void asetNet_payment() {
        this.net_payment = roundTwoDecimals(this.gross_Earnings - this.total_deductions);
    }
}
