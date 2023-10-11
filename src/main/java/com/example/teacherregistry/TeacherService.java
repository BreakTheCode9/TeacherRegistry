package com.example.teacherregistry;

import java.util.List;

public interface TeacherService {
    List<Teacher> listAll();
    void save(Teacher teacher);
    Teacher get(Integer id);
    void delete(Integer id);

}
