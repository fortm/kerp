// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kreativ.kerp.web;

import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.domain.ProjectRoleMap;
import com.kreativ.kerp.reference.Mst_job_role;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect ProjectRoleMapController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ProjectRoleMapController.createForm(Model uiModel) {
        uiModel.addAttribute("projectRoleMap", new ProjectRoleMap());
        List dependencies = new ArrayList();
        if (Project.countProjects() == 0) {
            dependencies.add(new String[]{"project", "projects"});
        }
        if (Mst_job_role.countMst_job_roles() == 0) {
            dependencies.add(new String[]{"mst_job_role", "mst_job_roles"});
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "projectrolemaps/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ProjectRoleMapController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("projectrolemap", ProjectRoleMap.findProjectRoleMap(id));
        uiModel.addAttribute("itemId", id);
        return "projectrolemaps/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ProjectRoleMapController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("projectrolemaps", ProjectRoleMap.findProjectRoleMapEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) ProjectRoleMap.countProjectRoleMaps() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("projectrolemaps", ProjectRoleMap.findAllProjectRoleMaps());
        }
        return "projectrolemaps/list";
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ProjectRoleMapController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("projectRoleMap", ProjectRoleMap.findProjectRoleMap(id));
        return "projectrolemaps/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ProjectRoleMapController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ProjectRoleMap.findProjectRoleMap(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/projectrolemaps";
    }
    
    @ModelAttribute("projects")
    public Collection<Project> ProjectRoleMapController.populateProjects() {
        return Project.findAllProjects();
    }
    
    @ModelAttribute("projectrolemaps")
    public Collection<ProjectRoleMap> ProjectRoleMapController.populateProjectRoleMaps() {
        return ProjectRoleMap.findAllProjectRoleMaps();
    }
    
    @ModelAttribute("mst_job_roles")
    public Collection<Mst_job_role> ProjectRoleMapController.populateMst_job_roles() {
        return Mst_job_role.findAllMst_job_roles();
    }
    
}
