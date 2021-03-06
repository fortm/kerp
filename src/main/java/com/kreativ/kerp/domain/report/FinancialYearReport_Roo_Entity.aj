// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.report;

import com.kreativ.kerp.domain.report.FinancialYearReport;
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

privileged aspect FinancialYearReport_Roo_Entity {
    
    declare @type: FinancialYearReport: @Entity;
    
    @PersistenceContext
    transient EntityManager FinancialYearReport.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long FinancialYearReport.id;
    
    @Version
    @Column(name = "version")
    private Integer FinancialYearReport.version;
    
    public Long FinancialYearReport.getId() {
        return this.id;
    }
    
    public void FinancialYearReport.setId(Long id) {
        this.id = id;
    }
    
    public Integer FinancialYearReport.getVersion() {
        return this.version;
    }
    
    public void FinancialYearReport.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void FinancialYearReport.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void FinancialYearReport.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            FinancialYearReport attached = FinancialYearReport.findFinancialYearReport(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void FinancialYearReport.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void FinancialYearReport.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public FinancialYearReport FinancialYearReport.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        FinancialYearReport merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager FinancialYearReport.entityManager() {
        EntityManager em = new FinancialYearReport().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long FinancialYearReport.countFinancialYearReports() {
        return entityManager().createQuery("SELECT COUNT(o) FROM FinancialYearReport o", Long.class).getSingleResult();
    }
    
    public static List<FinancialYearReport> FinancialYearReport.findAllFinancialYearReports() {
        return entityManager().createQuery("SELECT o FROM FinancialYearReport o", FinancialYearReport.class).getResultList();
    }
    
    public static FinancialYearReport FinancialYearReport.findFinancialYearReport(Long id) {
        if (id == null) return null;
        return entityManager().find(FinancialYearReport.class, id);
    }
    
    public static List<FinancialYearReport> FinancialYearReport.findFinancialYearReportEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM FinancialYearReport o", FinancialYearReport.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
