package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.StudentDao;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        System.out.println(studentDao.getAllStudents());
        return studentDao.getAllStudents();
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.deleteStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }
}
