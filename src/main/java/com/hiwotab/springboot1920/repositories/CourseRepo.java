package com.hiwotab.springboot1920.repositories;

import com.hiwotab.springboot1920.model.Courses;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Courses,Long>{

    Iterable<Courses>findCoursesById(Long Long);
}
