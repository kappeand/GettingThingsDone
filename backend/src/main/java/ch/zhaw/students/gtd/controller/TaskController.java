package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.Task;
import ch.zhaw.students.gtd.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskController {

    @Autowired
    private TaskRepository TaskRepository;

    public void persistTask(Task newTask, String name) {

    }

    public void updateTask(Task task, String name) {

    }

    public List<Task> readByOwner(String name) {
        return null;
    }
}