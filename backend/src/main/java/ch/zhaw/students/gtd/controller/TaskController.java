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
        Project project = projectRepository.findById(newTask.getProject().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));
        taskRepository.save(newTask);
        project.addTask(newTask);
        projectRepository.save(project);
    }

    public List<Task> readByOwner(String username) {
        return projectRepository.findByOwner(userRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")))
                .stream().map(project -> taskRepository.findByProject(project))
                .collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public List<Task> readByProject(Long id) {
        return taskRepository.findByProject(projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found.")));
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }
}