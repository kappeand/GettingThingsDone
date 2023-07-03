package ch.zhaw.students.gtd.boundary;

import ch.zhaw.students.gtd.controller.ProjectController;
import ch.zhaw.students.gtd.entity.Project;
import ch.zhaw.students.gtd.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ProjectEndpoint {

    @Autowired
    private ProjectController projectController;

    @RequestMapping(path = "/api/project", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Project> getProjects(Principal principal) {
        return projectController.listAllProjects(principal.getName());
    }

    @RequestMapping(path = "/api/project/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void addToDo(@RequestParam(name = "id") Long projectId, @RequestBody ToDo toDo, Principal principal) {
        projectController.addToDo(projectId, toDo, principal.getName());
    }
}