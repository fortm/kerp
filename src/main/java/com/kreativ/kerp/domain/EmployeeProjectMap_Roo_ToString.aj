// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain;

import java.lang.String;

privileged aspect EmployeeProjectMap_Roo_ToString {
    
    public String EmployeeProjectMap.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee: ").append(getEmployee()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Project: ").append(getProject()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
