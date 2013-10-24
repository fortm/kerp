package com.kreativ.kerp.reference;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findMst_job_rolesByJob_roleEquals" })
public class Mst_job_role {

	public Mst_job_role(String job_role) {
		super();
		this.job_role = job_role;
	}

	@NotNull
	@Column(unique = true)
	private String job_role;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getJob_role());
		return sb.toString();
	}

    public static String findMst_job_rolesByJob_IdEquals(Long id) {
        if (id == 0) throw new IllegalArgumentException("The job_role_id argument is required");
        EntityManager em = Mst_job_role.entityManager();
        TypedQuery<Mst_job_role> q = em.createQuery("SELECT o FROM Mst_job_role AS o WHERE o.id = :id", Mst_job_role.class);
        q.setParameter("id", id);
        return q.getResultList().get(0).toString();
    }
    
    public static Mst_job_role findMst_job_rolesByJob_roleEquals(String job_role) {
        if (job_role == null || job_role.length() == 0) throw new IllegalArgumentException("The job_role argument is required");
        EntityManager em = Mst_job_role.entityManager();
        TypedQuery<Mst_job_role> q = em.createQuery("SELECT o FROM Mst_job_role AS o WHERE o.job_role = :job_role", Mst_job_role.class);
        q.setParameter("job_role", job_role);
        return q.getResultList().get(0);
    }
	
}
