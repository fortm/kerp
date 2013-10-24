// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.payment;

import com.kreativ.kerp.payment.PFTable;
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

privileged aspect PFTable_Roo_Entity {
    
    declare @type: PFTable: @Entity;
    
    @PersistenceContext
    transient EntityManager PFTable.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PFTable.id;
    
    @Version
    @Column(name = "version")
    private Integer PFTable.version;
    
    public Long PFTable.getId() {
        return this.id;
    }
    
    public void PFTable.setId(Long id) {
        this.id = id;
    }
    
    public Integer PFTable.getVersion() {
        return this.version;
    }
    
    public void PFTable.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void PFTable.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PFTable.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PFTable attached = PFTable.findPFTable(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PFTable.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PFTable.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PFTable PFTable.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PFTable merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager PFTable.entityManager() {
        EntityManager em = new PFTable().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PFTable.countPFTables() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PFTable o", Long.class).getSingleResult();
    }
    
    public static List<PFTable> PFTable.findAllPFTables() {
        return entityManager().createQuery("SELECT o FROM PFTable o", PFTable.class).getResultList();
    }
    
    public static PFTable PFTable.findPFTable(Long id) {
        if (id == null) return null;
        return entityManager().find(PFTable.class, id);
    }
    
    public static List<PFTable> PFTable.findPFTableEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PFTable o", PFTable.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}