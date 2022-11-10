package peaksoft.dao;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorDao {
    List<Instructor> getAllInstructors();

    void saveInstructor(Instructor instructor);

    Instructor getInstructorById(Long id);

    void deleteInstructor(Instructor instructor);

    void updateInstructor(Instructor instructor);
}
