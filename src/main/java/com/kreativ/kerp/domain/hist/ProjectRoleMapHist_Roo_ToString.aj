// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.domain.hist;

import java.lang.String;

privileged aspect ProjectRoleMapHist_Roo_ToString {
    
    public String ProjectRoleMapHist.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atm: ").append(getAtm()).append(", ");
        sb.append("Basic: ").append(getBasic()).append(", ");
        sb.append("Canteen_allowance: ").append(getCanteen_allowance()).append(", ");
        sb.append("Conveyance_allowance: ").append(getConveyance_allowance()).append(", ");
        sb.append("Created: ").append(getCreated()).append(", ");
        sb.append("Da: ").append(getDa()).append(", ");
        sb.append("Educational_allowance: ").append(getEducational_allowance()).append(", ");
        sb.append("Ex_gratia: ").append(getEx_gratia()).append(", ");
        sb.append("Hra_percentage: ").append(getHra_percentage()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("LWF: ").append(getLWF()).append(", ");
        sb.append("LastUpdated: ").append(getLastUpdated()).append(", ");
        sb.append("Medical_allowance: ").append(getMedical_allowance()).append(", ");
        sb.append("Ot_rate: ").append(getOt_rate()).append(", ");
        sb.append("Other_allowance: ").append(getOther_allowance()).append(", ");
        sb.append("Project: ").append(getProject()).append(", ");
        sb.append("Role: ").append(getRole()).append(", ");
        sb.append("Sd: ").append(getSd()).append(", ");
        sb.append("Service_charges: ").append(getService_charges()).append(", ");
        sb.append("Special_allowance: ").append(getSpecial_allowance()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Washing_allowance: ").append(getWashing_allowance()).append(", ");
        sb.append("Bonus_flag: ").append(isBonus_flag());
        return sb.toString();
    }
    
}
