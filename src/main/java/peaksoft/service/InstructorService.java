package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();

    void saveInstructor(Instructor teacher, Long courseId);

    Instructor getInstructorById(Long id);

    void deleteInstructor(Instructor teacher);

    void updateInstructor(Instructor teacher, Long courseId);
}
