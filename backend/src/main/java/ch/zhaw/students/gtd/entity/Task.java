package ch.zhaw.students.gtd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_name")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}