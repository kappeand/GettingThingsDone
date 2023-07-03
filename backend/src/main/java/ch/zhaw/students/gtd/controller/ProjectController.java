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

    public void create(Project newProject) {
        projectRepository.save(newProject);
    }

    public List<Project> readByOwner(String ownerName) {
        return projectRepository.findByOwner(ownerName);
    }

    public void update(Project project) {
        projectRepository.save(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public void addTask(Long projectId, Task task) {
        Project project = projectRepository.getOne(projectId);
        project.getTasks().add(task);
        projectRepository.save(project);
    }
}