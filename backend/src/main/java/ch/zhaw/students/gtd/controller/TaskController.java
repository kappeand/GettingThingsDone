package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public void create(Task newTask) {
        Project project = projectRepository.findById(newTask.getProjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));
        taskRepository.save(newTask);
        project.addTask(newTask);
        projectRepository.save(project);
    }

    public List<Task> readByOwner(String username) {
        return projectRepository.findByOwner(userRepository.getReferenceById(username))
                .stream().map(Project::getTasks).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<Task> readByProject(Long id) {
        return taskRepository.findByProjectIdOrderByPriorityDescDueDateDesc(id);
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}