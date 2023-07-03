package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.Project;
import ch.zhaw.students.gtd.entity.ProjectRepository;
import ch.zhaw.students.gtd.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> readByOwner(String name) {

        return null;
    }

    public void addTask(Long projectId, Task task, String name) {

    }
}