package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.Project;
import ch.zhaw.students.gtd.entity.Task;
import ch.zhaw.students.gtd.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    public void create(Task newTask) {
        taskRepository.save(newTask);
    }

    public List<Task> readByProject(Long id) {
        return null;
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }
}