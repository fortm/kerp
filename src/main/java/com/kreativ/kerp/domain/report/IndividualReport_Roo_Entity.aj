// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.report;

import com.kreativ.kerp.domain.report.IndividualReport;
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

privileged aspect IndividualReport_Roo_Entity {
    
    declare @type: IndividualReport: @Entity;
    
    @PersistenceContext
    transient EntityManager IndividualReport.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long IndividualReport.id;
    
    @Version
    @Column(name = "version")
    private Integer IndividualReport.version;
    
    public Long IndividualReport.getId() {
        return this.id;
    }
    
    public void IndividualReport.setId(Long id) {
        this.id = id;
    }
    
    public Integer IndividualReport.getVersion() {
        return this.version;
    }
    
    public void IndividualReport.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void IndividualReport.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void IndividualReport.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            IndividualReport attached = IndividualReport.findIndividualReport(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void IndividualReport.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void IndividualReport.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public IndividualReport IndividualReport.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        IndividualReport merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager IndividualReport.entityManager() {
        EntityManager em = new IndividualReport().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long IndividualReport.countIndividualReports() {
        return entityManager().createQuery("SELECT COUNT(o) FROM IndividualReport o", Long.class).getSingleResult();
    }
    
    public static List<IndividualReport> IndividualReport.findAllIndividualReports() {
        return entityManager().createQuery("SELECT o FROM IndividualReport o", IndividualReport.class).getResultList();
    }
    
    public static IndividualReport IndividualReport.findIndividualReport(Long id) {
        if (id == null) return null;
        return entityManager().find(IndividualReport.class, id);
    }
    
    public static List<IndividualReport> IndividualReport.findIndividualReportEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM IndividualReport o", IndividualReport.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
