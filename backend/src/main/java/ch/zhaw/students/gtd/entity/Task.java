package ch.zhaw.students.gtd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    private int priority;

    private boolean done;

    private Long projectId;

    public Task() {
    }

    public Task(String name, String description, Date dueDate, int priority, boolean done, Long projectId) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.done = done;
        this.projectId = projectId;
    }
}