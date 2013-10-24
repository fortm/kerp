package com.kreativ.kerp.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import ca.digitalface.jasperoo.RooJasperoo;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findEmployeeBankDetailsesById" })
public class EmployeeBankDetails {

    @NotNull
    @ManyToOne
    private Employee employee;

    private String role;

    @NotNull
    private String bankName;

    @NotNull
    private String account_number;

    @NotNull
    private String atm_card_number;

    @NotNull
    private String customer_id;
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account_number: ").append(getAccount_number()).append(", ");
        sb.append("Atm_card_number: ").append(getAtm_card_number()).append(", ");
        sb.append("BankName: ").append(getBankName()).append(", ");
        sb.append("Customer_id: ").append(getCustomer_id()).append(", ");
        sb.append("Employee: ").append(getEmployee()).append(", ");
        sb.append("Role: ").append(getRole());
        return sb.toString();
    }
        
}
