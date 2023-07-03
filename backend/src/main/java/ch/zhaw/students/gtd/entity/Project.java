package ch.zhaw.students.gtd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_login_name", nullable = false)
    @JsonIgnore
    private User owner;

    public Project() {
    }

    public Project(String name, List<Task> tasks, User owner) {
        this.name = name;
        this.tasks = tasks;
        this.owner = owner;
    }
}