// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.reference;

import com.kreativ.kerp.reference.Mst_job_statusDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Mst_job_statusIntegrationTest_Roo_IntegrationTest {
    
    declare @type: Mst_job_statusIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: Mst_job_statusIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: Mst_job_statusIntegrationTest: @Transactional;
    
    @Autowired
    private Mst_job_statusDataOnDemand Mst_job_statusIntegrationTest.dod;
    
    @Test
    public void Mst_job_statusIntegrationTest.testCountMst_job_statuses() {
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", dod.getRandomMst_job_status());
        long count = com.kreativ.kerp.reference.Mst_job_status.countMst_job_statuses();
        org.junit.Assert.assertTrue("Counter for 'Mst_job_status' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testFindMst_job_status() {
        com.kreativ.kerp.reference.Mst_job_status obj = dod.getRandomMst_job_status();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to provide an identifier", id);
        obj = com.kreativ.kerp.reference.Mst_job_status.findMst_job_status(id);
        org.junit.Assert.assertNotNull("Find method for 'Mst_job_status' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Mst_job_status' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testFindAllMst_job_statuses() {
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", dod.getRandomMst_job_status());
        long count = com.kreativ.kerp.reference.Mst_job_status.countMst_job_statuses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Mst_job_status', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.kreativ.kerp.reference.Mst_job_status> result = com.kreativ.kerp.reference.Mst_job_status.findAllMst_job_statuses();
        org.junit.Assert.assertNotNull("Find all method for 'Mst_job_status' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Mst_job_status' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testFindMst_job_statusEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", dod.getRandomMst_job_status());
        long count = com.kreativ.kerp.reference.Mst_job_status.countMst_job_statuses();
        if (count > 20) count = 20;
        java.util.List<com.kreativ.kerp.reference.Mst_job_status> result = com.kreativ.kerp.reference.Mst_job_status.findMst_job_statusEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Mst_job_status' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Mst_job_status' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testFlush() {
        com.kreativ.kerp.reference.Mst_job_status obj = dod.getRandomMst_job_status();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to provide an identifier", id);
        obj = com.kreativ.kerp.reference.Mst_job_status.findMst_job_status(id);
        org.junit.Assert.assertNotNull("Find method for 'Mst_job_status' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMst_job_status(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Mst_job_status' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testMerge() {
        com.kreativ.kerp.reference.Mst_job_status obj = dod.getRandomMst_job_status();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to provide an identifier", id);
        obj = com.kreativ.kerp.reference.Mst_job_status.findMst_job_status(id);
        boolean modified =  dod.modifyMst_job_status(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.kreativ.kerp.reference.Mst_job_status merged = (com.kreativ.kerp.reference.Mst_job_status) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Mst_job_status' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", dod.getRandomMst_job_status());
        com.kreativ.kerp.reference.Mst_job_status obj = dod.getNewTransientMst_job_status(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Mst_job_status' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Mst_job_status' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void Mst_job_statusIntegrationTest.testRemove() {
        com.kreativ.kerp.reference.Mst_job_status obj = dod.getRandomMst_job_status();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Mst_job_status' failed to provide an identifier", id);
        obj = com.kreativ.kerp.reference.Mst_job_status.findMst_job_status(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Mst_job_status' with identifier '" + id + "'", com.kreativ.kerp.reference.Mst_job_status.findMst_job_status(id));
    }
    
}
