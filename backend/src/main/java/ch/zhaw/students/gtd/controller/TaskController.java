package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.ProjectRepository;
import ch.zhaw.students.gtd.entity.Task;
import ch.zhaw.students.gtd.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void create(Task newTask) {
        taskRepository.save(newTask);
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