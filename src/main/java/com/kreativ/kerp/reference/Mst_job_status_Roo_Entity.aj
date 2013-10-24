// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.reference;

import com.kreativ.kerp.reference.Mst_job_status;
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

privileged aspect Mst_job_status_Roo_Entity {
    
    declare @type: Mst_job_status: @Entity;
    
    @PersistenceContext
    transient EntityManager Mst_job_status.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Mst_job_status.id;
    
    @Version
    @Column(name = "version")
    private Integer Mst_job_status.version;
    
    public Mst_job_status.new() {
        super();
    }

    public Long Mst_job_status.getId() {
        return this.id;
    }
    
    public void Mst_job_status.setId(Long id) {
        this.id = id;
    }
    
    public Integer Mst_job_status.getVersion() {
        return this.version;
    }
    
    public void Mst_job_status.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Mst_job_status.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Mst_job_status.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Mst_job_status attached = Mst_job_status.findMst_job_status(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Mst_job_status.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Mst_job_status.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Mst_job_status Mst_job_status.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Mst_job_status merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Mst_job_status.entityManager() {
        EntityManager em = new Mst_job_status().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Mst_job_status.countMst_job_statuses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Mst_job_status o", Long.class).getSingleResult();
    }
    
    public static List<Mst_job_status> Mst_job_status.findAllMst_job_statuses() {
        return entityManager().createQuery("SELECT o FROM Mst_job_status o", Mst_job_status.class).getResultList();
    }
    
    public static Mst_job_status Mst_job_status.findMst_job_status(Long id) {
        if (id == null) return null;
        return entityManager().find(Mst_job_status.class, id);
    }
    
    public static List<Mst_job_status> Mst_job_status.findMst_job_statusEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Mst_job_status o", Mst_job_status.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
