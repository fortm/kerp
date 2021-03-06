// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.hist;

import com.kreativ.kerp.domain.hist.EmployeeHist;
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

privileged aspect EmployeeHist_Roo_Entity {
    
    declare @type: EmployeeHist: @Entity;
    
    @PersistenceContext
    transient EntityManager EmployeeHist.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long EmployeeHist.id;
    
    @Version
    @Column(name = "version")
    private Integer EmployeeHist.version;
    
    public EmployeeHist.new() {
        super();
    }

    public Long EmployeeHist.getId() {
        return this.id;
    }
    
    public void EmployeeHist.setId(Long id) {
        this.id = id;
    }
    
    public Integer EmployeeHist.getVersion() {
        return this.version;
    }
    
    public void EmployeeHist.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void EmployeeHist.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EmployeeHist.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EmployeeHist attached = EmployeeHist.findEmployeeHist(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EmployeeHist.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EmployeeHist.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EmployeeHist EmployeeHist.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EmployeeHist merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager EmployeeHist.entityManager() {
        EntityManager em = new EmployeeHist().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EmployeeHist.countEmployeeHists() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EmployeeHist o", Long.class).getSingleResult();
    }
    
    public static List<EmployeeHist> EmployeeHist.findAllEmployeeHists() {
        return entityManager().createQuery("SELECT o FROM EmployeeHist o", EmployeeHist.class).getResultList();
    }
    
    public static EmployeeHist EmployeeHist.findEmployeeHist(Long id) {
        if (id == null) return null;
        return entityManager().find(EmployeeHist.class, id);
    }
    
    public static List<EmployeeHist> EmployeeHist.findEmployeeHistEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EmployeeHist o", EmployeeHist.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
