// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.ProjectDataOnDemand;
import com.kreativ.kerp.reference.Mst_job_statusDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect EmployeeDataOnDemand_Roo_DataOnDemand {
    
    declare @type: EmployeeDataOnDemand: @Component;
    
    private Random EmployeeDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Employee> EmployeeDataOnDemand.data;
    
    @Autowired
    private ProjectDataOnDemand EmployeeDataOnDemand.projectDataOnDemand;
    
    @Autowired
    private Mst_job_statusDataOnDemand EmployeeDataOnDemand.mst_job_statusDataOnDemand;
    
    public Employee EmployeeDataOnDemand.getNewTransientEmployee(int index) {
        com.kreativ.kerp.domain.Employee obj = new com.kreativ.kerp.domain.Employee();
        setFirst_name(obj, index);
        setLast_name(obj, index);
        setUser_name(obj, index);
        setAddress_line_1(obj, index);
        setAddress_line_2(obj, index);
        setCity(obj, index);
        setCountry(obj, index);
        setPaymentMode(obj, index);
        setGender(obj, index);
        setJoining_date(obj, index);
        setStatus(obj, index);
        setEmail(obj, index);
        setWorkArea(obj, index);
        setProject(obj, index);
        setMst_job_role(obj, index);
        setMst_job_status(obj, index);
        return obj;
    }
    
    public void EmployeeDataOnDemand.setFirst_name(Employee obj, int index) {
        java.lang.String first_name = "first_name_" + index;
        obj.setFirst_name(first_name);
    }
    
    public void EmployeeDataOnDemand.setLast_name(Employee obj, int index) {
        java.lang.String last_name = "last_name_" + index;
        obj.setLast_name(last_name);
    }
    
    public void EmployeeDataOnDemand.setUser_name(Employee obj, int index) {
        java.lang.String user_name = "user_name_" + index;
        obj.setUser_name(user_name);
    }
    
    public void EmployeeDataOnDemand.setAddress_line_1(Employee obj, int index) {
        java.lang.String address_line_1 = "address_line_1_" + index;
        obj.setAddress_line_1(address_line_1);
    }
    
    public void EmployeeDataOnDemand.setAddress_line_2(Employee obj, int index) {
        java.lang.String address_line_2 = "address_line_2_" + index;
        obj.setAddress_line_2(address_line_2);
    }
    
    public void EmployeeDataOnDemand.setCity(Employee obj, int index) {
        java.lang.String city = "city_" + index;
        obj.setCity(city);
    }
    
    public void EmployeeDataOnDemand.setCountry(Employee obj, int index) {
        java.lang.String country = "country_" + index;
        obj.setCountry(country);
    }
    
    public void EmployeeDataOnDemand.setPaymentMode(Employee obj, int index) {
        com.kreativ.kerp.reference.PaymentMode paymentMode = com.kreativ.kerp.reference.PaymentMode.class.getEnumConstants()[0];
        obj.setPaymentMode(paymentMode);
    }
    
    public void EmployeeDataOnDemand.setGender(Employee obj, int index) {
        com.kreativ.kerp.reference.Gender gender = com.kreativ.kerp.reference.Gender.class.getEnumConstants()[0];
        obj.setGender(gender);
    }
    
    public void EmployeeDataOnDemand.setJoining_date(Employee obj, int index) {
        java.util.Date joining_date = new java.util.GregorianCalendar(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY), java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE), java.util.Calendar.getInstance().get(java.util.Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setJoining_date(joining_date);
    }
    
    public void EmployeeDataOnDemand.setStatus(Employee obj, int index) {
        com.kreativ.kerp.reference.Status status = com.kreativ.kerp.reference.Status.class.getEnumConstants()[0];
        obj.setStatus(status);
    }
    
    public void EmployeeDataOnDemand.setEmail(Employee obj, int index) {
        java.lang.String email = "email_" + index;
        obj.setEmail(email);
    }
    
    public void EmployeeDataOnDemand.setWorkArea(Employee obj, int index) {
        com.kreativ.kerp.reference.WorkArea workArea = com.kreativ.kerp.reference.WorkArea.class.getEnumConstants()[0];
        obj.setWorkArea(workArea);
    }
    
    public void EmployeeDataOnDemand.setProject(Employee obj, int index) {
        com.kreativ.kerp.domain.Project project = projectDataOnDemand.getRandomProject();
        obj.setProject(project);
    }
    
    public void EmployeeDataOnDemand.setMst_job_role(Employee obj, int index) {
        com.kreativ.kerp.reference.Mst_job_role mst_job_role = null;
        obj.setMst_job_role(mst_job_role);
    }
    
    public void EmployeeDataOnDemand.setMst_job_status(Employee obj, int index) {
        com.kreativ.kerp.reference.Mst_job_status mst_job_status = mst_job_statusDataOnDemand.getRandomMst_job_status();
        obj.setMst_job_status(mst_job_status);
    }
    
    public Employee EmployeeDataOnDemand.getSpecificEmployee(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Employee obj = data.get(index);
        return Employee.findEmployee(obj.getEmployee_id());
    }
    
    public Employee EmployeeDataOnDemand.getRandomEmployee() {
        init();
        Employee obj = data.get(rnd.nextInt(data.size()));
        return Employee.findEmployee(obj.getEmployee_id());
    }
    
    public boolean EmployeeDataOnDemand.modifyEmployee(Employee obj) {
        return false;
    }
    
    public void EmployeeDataOnDemand.init() {
        data = com.kreativ.kerp.domain.Employee.findEmployeeEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Employee' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.kreativ.kerp.domain.Employee>();
        for (int i = 0; i < 10; i++) {
            com.kreativ.kerp.domain.Employee obj = getNewTransientEmployee(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
