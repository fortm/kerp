// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.hist;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.hist.EmployeeHist;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect EmployeeHist_Roo_Finder {
    
    public static TypedQuery<EmployeeHist> EmployeeHist.findEmployeeHistsByEmployee(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("The employee argument is required");
        EntityManager em = EmployeeHist.entityManager();
        TypedQuery<EmployeeHist> q = em.createQuery("SELECT o FROM EmployeeHist AS o WHERE o.employee = :employee", EmployeeHist.class);
        q.setParameter("employee", employee);
        return q;
    }
    
}
