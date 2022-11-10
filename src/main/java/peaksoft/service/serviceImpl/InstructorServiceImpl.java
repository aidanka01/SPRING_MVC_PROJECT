package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.dao.InstructorDao;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorDao instructorDao;
    private final CourseDao courseDao;

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao, CourseDao courseDao) {
        this.instructorDao = instructorDao;
        this.courseDao = courseDao;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorDao.getAllInstructors();
    }

    @Override
    public void saveInstructor(Instructor instructor, Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        instructor.setCourse(course);
        instructorDao.saveInstructor(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorDao.getInstructorById(id);
    }

    @Override
    public void deleteInstructor(Instructor instructor) {
        instructorDao.deleteInstructor(instructor);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long courseId) {
        instructor.setCourse(courseDao.getCourseById(courseId));
        instructorDao.updateInstructor(instructor);
    }
}


