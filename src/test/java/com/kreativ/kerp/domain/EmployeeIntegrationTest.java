package com.kreativ.kerp.domain;

import org.springframework.roo.addon.test.RooIntegrationTest;
import com.kreativ.kerp.domain.Employee;
import org.junit.Test;

@RooIntegrationTest(entity = Employee.class)
public class EmployeeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
