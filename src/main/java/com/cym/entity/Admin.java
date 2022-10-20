package com.cym.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
public class Admin extends User {
    @Column(name="teacherIds")
    @ElementCollection(targetClass=Long.class)
    private List<Long> teacherIds;

    @Column(name="studentIds")
    @ElementCollection(targetClass=Long.class)
    private List<Long> studentIds;

    @Column(name="guardianIds")
    @ElementCollection(targetClass=Long.class)
    private List<Long> guardianIds;

    @OneToMany
    private List<Languages> languages;
}
