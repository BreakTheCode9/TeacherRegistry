package com.example.teacherregistry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.listAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Integer id) throws TeacherNotFoundException {
        Teacher teacher = teacherService.get(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return new ResponseEntity<>("Teacher created successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id") Integer id) throws TeacherNotFoundException {
        teacherService.delete(id);
        return new ResponseEntity<>("Teacher deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
