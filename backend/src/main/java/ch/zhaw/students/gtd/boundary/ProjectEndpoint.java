package ch.zhaw.students.gtd.boundary;

import ch.zhaw.students.gtd.controller.ProjectController;
import ch.zhaw.students.gtd.entity.Project;
import ch.zhaw.students.gtd.entity.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/api/project", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void createProject(@RequestBody Project project, Principal principal) {
        projectController.create(project, principal.getName());
    }

    @RequestMapping(path = "/api/project", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Project> readProjectsByOwner(Principal principal) {
        return projectController.readByOwner(principal.getName());
    }

    @RequestMapping(path = "/api/project", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void updateProject(@RequestBody Project project, Principal principal) {
        projectController.update(project, principal.getName());
    }

    @RequestMapping(path = "/api/project", method = RequestMethod.DELETE)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void deleteProject(@RequestParam Long projectId) {
        projectController.delete(projectId);
    }
}