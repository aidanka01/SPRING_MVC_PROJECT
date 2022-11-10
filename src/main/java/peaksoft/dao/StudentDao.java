package peaksoft.dao;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();

    void saveStudent(Student student);

    Student getStudentById(Long id);

    void deleteStudent(Student student);

    void updateStudent(Student student);
}
