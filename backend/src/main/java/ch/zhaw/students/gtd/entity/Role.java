package ch.zhaw.students.gtd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private String roleName;

}