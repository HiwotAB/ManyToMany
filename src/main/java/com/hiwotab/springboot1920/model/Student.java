package com.hiwotab.springboot1920.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

    @Entity
    public class Student {


        @NotNull
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @NotEmpty
        @Size(max=20)
        private String firstname;
        @NotEmpty
        @Size(max=20)
        private String lastname;
        @NotEmpty
        @Email
        private String email;
        @ManyToMany(mappedBy ="people")
        private  Set<Courses>courses;
        public Student()
        {
           courses=new HashSet<Courses>();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public Set<Courses> getCourses() {
            return courses;
        }

        public void setCourses(Set<Courses> courses) {
            this.courses = courses;
        }



        public void addCourses(Courses crs)
        {
            courses.add(crs);
        }

    }
