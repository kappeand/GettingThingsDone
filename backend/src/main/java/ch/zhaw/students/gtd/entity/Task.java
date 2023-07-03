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

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private boolean done;

    @ManyToOne
    private Project project;
}