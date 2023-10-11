package com.example.teacherregistry;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message){
        super(message);
    }
}
