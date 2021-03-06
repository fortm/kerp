// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.reference;

import com.kreativ.kerp.reference.Mst_job_status;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect Mst_job_statusDataOnDemand_Roo_DataOnDemand {
    
    declare @type: Mst_job_statusDataOnDemand: @Component;
    
    private Random Mst_job_statusDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Mst_job_status> Mst_job_statusDataOnDemand.data;
    
    public Mst_job_status Mst_job_statusDataOnDemand.getNewTransientMst_job_status(int index) {
        com.kreativ.kerp.reference.Mst_job_status obj = new com.kreativ.kerp.reference.Mst_job_status();
        setJob_status(obj, index);
        return obj;
    }
    
    public void Mst_job_statusDataOnDemand.setJob_status(Mst_job_status obj, int index) {
        java.lang.String job_status = "job_status_" + index;
        obj.setJob_status(job_status);
    }
    
    public Mst_job_status Mst_job_statusDataOnDemand.getSpecificMst_job_status(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Mst_job_status obj = data.get(index);
        return Mst_job_status.findMst_job_status(obj.getId());
    }
    
    public Mst_job_status Mst_job_statusDataOnDemand.getRandomMst_job_status() {
        init();
        Mst_job_status obj = data.get(rnd.nextInt(data.size()));
        return Mst_job_status.findMst_job_status(obj.getId());
    }
    
    public boolean Mst_job_statusDataOnDemand.modifyMst_job_status(Mst_job_status obj) {
        return false;
    }
    
    public void Mst_job_statusDataOnDemand.init() {
        data = com.kreativ.kerp.reference.Mst_job_status.findMst_job_statusEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Mst_job_status' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.kreativ.kerp.reference.Mst_job_status>();
        for (int i = 0; i < 10; i++) {
            com.kreativ.kerp.reference.Mst_job_status obj = getNewTransientMst_job_status(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
