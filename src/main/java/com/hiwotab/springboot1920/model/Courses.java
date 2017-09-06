package com.hiwotab.springboot1920.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String course;

    public Courses() {
        this.people = new HashSet<Student>();
    }

    @ManyToMany
    private Set<Student> people;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Set<Student> getPeople() {
        return people;
    }

    public void setPeople(Set<Student> people) {
        this.people = people;
    }

    public void addStudent(Student pers) {
        people.add(pers);
    }
}
