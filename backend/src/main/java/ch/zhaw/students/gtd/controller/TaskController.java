package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.ProjectRepository;
import ch.zhaw.students.gtd.entity.Task;
import ch.zhaw.students.gtd.entity.TaskRepository;
import ch.zhaw.students.gtd.entity.UserRepository;
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

    public void create(Task newTask, Long projectId) {
        projectRepository.findById(projectId).map(project -> {
            project.addTask(newTask);
            newTask.setProject(project);
            taskRepository.save(newTask);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));
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

    public List<Task> readByOwner(String username) {
        return projectRepository.findByOwner(userRepository.findById(username).get()).stream().map(project -> taskRepository.findByProject(project)).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public List<Task> readAll() {
        return taskRepository.findAll();
    }
}