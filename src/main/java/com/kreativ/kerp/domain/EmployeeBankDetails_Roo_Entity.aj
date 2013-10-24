// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.EmployeeBankDetails;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EmployeeBankDetails_Roo_Entity {
    
    declare @type: EmployeeBankDetails: @Entity;
    
    @PersistenceContext
    transient EntityManager EmployeeBankDetails.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long EmployeeBankDetails.id;
    
    @Version
    @Column(name = "version")
    private Integer EmployeeBankDetails.version;
    
    public Long EmployeeBankDetails.getId() {
        return this.id;
    }
    
    public void EmployeeBankDetails.setId(Long id) {
        this.id = id;
    }
    
    public Integer EmployeeBankDetails.getVersion() {
        return this.version;
    }
    
    public void EmployeeBankDetails.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void EmployeeBankDetails.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EmployeeBankDetails.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EmployeeBankDetails attached = EmployeeBankDetails.findEmployeeBankDetails(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EmployeeBankDetails.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EmployeeBankDetails.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EmployeeBankDetails EmployeeBankDetails.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EmployeeBankDetails merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager EmployeeBankDetails.entityManager() {
        EntityManager em = new EmployeeBankDetails().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EmployeeBankDetails.countEmployeeBankDetailses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EmployeeBankDetails o", Long.class).getSingleResult();
    }
    
    public static List<EmployeeBankDetails> EmployeeBankDetails.findAllEmployeeBankDetailses() {
        return entityManager().createQuery("SELECT o FROM EmployeeBankDetails o", EmployeeBankDetails.class).getResultList();
    }
    
    public static EmployeeBankDetails EmployeeBankDetails.findEmployeeBankDetails(Long id) {
        if (id == null) return null;
        return entityManager().find(EmployeeBankDetails.class, id);
    }
    
    public static List<EmployeeBankDetails> EmployeeBankDetails.findEmployeeBankDetailsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EmployeeBankDetails o", EmployeeBankDetails.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}