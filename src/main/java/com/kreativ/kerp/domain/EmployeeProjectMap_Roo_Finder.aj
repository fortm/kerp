// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.EmployeeProjectMap;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect EmployeeProjectMap_Roo_Finder {
    
    public static TypedQuery<EmployeeProjectMap> EmployeeProjectMap.findEmployeeProjectMapsByEmployee(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("The employee argument is required");
        EntityManager em = EmployeeProjectMap.entityManager();
        TypedQuery<EmployeeProjectMap> q = em.createQuery("SELECT o FROM EmployeeProjectMap AS o WHERE o.employee = :employee", EmployeeProjectMap.class);
        q.setParameter("employee", employee);
        return q;
    }
    
}