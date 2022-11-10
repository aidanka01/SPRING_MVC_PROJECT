package peaksoft.dao.daoimpl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.StudentDao;
import peaksoft.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
//        Comparator<Student> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
//        students.sort(comparator);
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
}
