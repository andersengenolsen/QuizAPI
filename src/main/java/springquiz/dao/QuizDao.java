package springquiz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springquiz.entity.Question;

import java.util.List;

/**
 * Repository for the admin, editing questions in quiz.
 */
@Repository
public class QuizDao implements Dao<Question> {

    @Autowired
    private SessionFactory factory;

    /**
     * @return all questions
     */
    @Override
    public List<Question> getAll() {
        Session session = factory.getCurrentSession();
        Query<Question> query = session.createQuery("from Question", Question.class);
        return query.getResultList();
    }

    /**
     * @param question to save
     */
    @Override
    public void save(Question question) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(question);
    }

    /**
     * @param id question id
     * @return question with given id
     */
    @Override
    public Question get(int id) {
        Session session = factory.getCurrentSession();
        return session.get(Question.class, id);
    }

    /**
     * @param id Id of object T to delete
     */
    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        Query query =
                session.createQuery("delete from Question where id=:qId");
        query.setParameter("qId", id);

        query.executeUpdate();
    }

    /**
     * @param question to delete
     */
    @Override
    public void delete(Question question) {
        if (question.getId() == null)
            throw new IllegalArgumentException("Customer must have valid id");

        Session session = factory.getCurrentSession();
        session.delete(question);
    }
}
