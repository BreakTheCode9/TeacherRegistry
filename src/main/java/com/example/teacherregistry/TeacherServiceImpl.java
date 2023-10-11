package com.example.teacherregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repo;

    @Autowired
    public TeacherServiceImpl(TeacherRepository repo) {
        this.repo = repo;
    }
    public List<Teacher> listAll() {
        return (List<Teacher>) repo.findAll();
    }

    public void save(Teacher teacher) {
        repo.save(teacher);
    }

    public Teacher get(Integer id) throws TeacherNotFoundException {
        Optional<Teacher> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new TeacherNotFoundException("Could not find student with ID " + id);
    }

    public void delete(Integer id) throws TeacherNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new TeacherNotFoundException("Could not find student with ID " + id);
        }
        repo.deleteById(id);
    }

    public Teacher create(Integer id) throws TeacherNotFoundException {
        Optional<Teacher> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new TeacherNotFoundException("Could not find student with ID " + id);
    }

    public void cancel(Integer id) throws TeacherNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new TeacherNotFoundException("Could not find student with ID " + id);
        }
        repo.deleteById(id);
    }
}
