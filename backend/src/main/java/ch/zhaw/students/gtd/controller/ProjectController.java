package ch.zhaw.students.gtd.controller;

import ch.zhaw.students.gtd.entity.Project;
import ch.zhaw.students.gtd.entity.ProjectRepository;
import ch.zhaw.students.gtd.entity.Task;
import ch.zhaw.students.gtd.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public void create(Project newProject, String ownerName) {
        newProject.setOwner(userRepository.getReferenceById(ownerName));
        projectRepository.save(newProject);
    }

    public List<Project> readByOwner(String ownerName) {
        return projectRepository.findByOwner(userRepository.getReferenceById(ownerName));
    }

    public void update(Project project) {
        projectRepository.save(project);
    }

    public void delete(Long projectId) {
        projectRepository.delete(projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found.")));
    }

    public void addTask(Long projectId, Task task) {
        projectRepository.findById(projectId).ifPresentOrElse(project -> {
            project.getTasks().add(task);
            projectRepository.save(project);
        }, () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));
    }
}