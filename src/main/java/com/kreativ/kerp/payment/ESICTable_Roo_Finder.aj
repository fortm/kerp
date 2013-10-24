// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.payment;

import com.kreativ.kerp.payment.ESICTable;
import com.kreativ.kerp.reference.TYPE;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ESICTable_Roo_Finder {
    
    public static TypedQuery<ESICTable> ESICTable.findESICTablesByTypeEquals(TYPE type) {
        if (type == null) throw new IllegalArgumentException("The type argument is required");
        EntityManager em = ESICTable.entityManager();
        TypedQuery<ESICTable> q = em.createQuery("SELECT o FROM ESICTable AS o WHERE o.type = :type", ESICTable.class);
        q.setParameter("type", type);
        return q;
    }
    
}
