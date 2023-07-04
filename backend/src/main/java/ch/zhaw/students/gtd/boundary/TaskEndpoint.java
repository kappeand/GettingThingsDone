package ch.zhaw.students.gtd.boundary;

import ch.zhaw.students.gtd.controller.TaskController;
import ch.zhaw.students.gtd.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class TaskEndpoint {

    @Autowired
    private TaskController taskController;

    @RequestMapping(path = "/api/task", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void createTask(@RequestParam Long projectId, @RequestBody Task task) {
        taskController.create(task, projectId);
    }

    @RequestMapping(path = "/api/task", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Task> getTasksByOwner(Principal principal) {
        return taskController.readByOwner(principal.getName());
    }

    @RequestMapping(path = "/api/task", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void updateTask(@RequestBody Task task) {
        taskController.update(task);
    }

    @RequestMapping(path = "/api/task", method = RequestMethod.DELETE)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void deleteTask(@RequestBody Task task) {
        taskController.delete(task);
    }
}