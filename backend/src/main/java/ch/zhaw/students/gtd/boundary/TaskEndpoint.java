package ch.zhaw.students.gtd.boundary;

import ch.zhaw.students.gtd.controller.TaskController;
import ch.zhaw.students.gtd.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class TaskEndpoint {

    @Autowired
    private TaskController taskController;

    @RequestMapping(path = "/api/task/{projectId}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Task> getTasks(@PathVariable Long projectId) {
        return taskController.readByProject(projectId);
    }

    @RequestMapping(path = "/api/task", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void updateTask(@RequestBody Task task) {
        taskController.update(task);
    }
}