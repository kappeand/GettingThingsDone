package ch.zhaw.students.gtd.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id
    private String name;
}