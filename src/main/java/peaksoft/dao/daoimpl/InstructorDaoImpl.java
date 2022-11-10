package peaksoft.dao.daoimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.CourseDao;
import peaksoft.dao.InstructorDao;
import peaksoft.entity.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class InstructorDaoImpl implements InstructorDao {
    @PersistenceContext
    EntityManager entityManager;

    private final CourseDao courseDao;

    @Autowired
    public InstructorDaoImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }



    @Override
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = entityManager.createQuery("from Instructor", Instructor.class).getResultList();
        Comparator<Instructor> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        instructors.sort(comparator);
        return instructors;
    }




    @Override
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void deleteInstructor(Instructor instructor) {
        entityManager.remove(entityManager.contains(instructor) ? instructor : entityManager.merge(instructor));

    }

    @Override
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);

    }
}
