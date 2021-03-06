// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import com.kreativ.kerp.domain.MonthsInYear;
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

privileged aspect MonthsInYear_Roo_Entity {
    
    declare @type: MonthsInYear: @Entity;
    
    @PersistenceContext
    transient EntityManager MonthsInYear.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long MonthsInYear.id;
    
    @Version
    @Column(name = "version")
    private Integer MonthsInYear.version;
    
    public Long MonthsInYear.getId() {
        return this.id;
    }
    
    public void MonthsInYear.setId(Long id) {
        this.id = id;
    }
    
    public Integer MonthsInYear.getVersion() {
        return this.version;
    }
    
    public void MonthsInYear.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void MonthsInYear.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MonthsInYear.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MonthsInYear attached = MonthsInYear.findMonthsInYear(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MonthsInYear.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MonthsInYear.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MonthsInYear MonthsInYear.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MonthsInYear merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager MonthsInYear.entityManager() {
        EntityManager em = new MonthsInYear().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MonthsInYear.countMonthsInYears() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MonthsInYear o", Long.class).getSingleResult();
    }
    
    public static List<MonthsInYear> MonthsInYear.findAllMonthsInYears() {
        return entityManager().createQuery("SELECT o FROM MonthsInYear o", MonthsInYear.class).getResultList();
    }
    
    public static MonthsInYear MonthsInYear.findMonthsInYear(Long id) {
        if (id == null) return null;
        return entityManager().find(MonthsInYear.class, id);
    }
    
    public static List<MonthsInYear> MonthsInYear.findMonthsInYearEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MonthsInYear o", MonthsInYear.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
