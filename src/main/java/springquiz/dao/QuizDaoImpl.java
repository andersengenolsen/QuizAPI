package springquiz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springquiz.entity.Category;
import springquiz.entity.Question;

import java.util.List;

/**
 * Repository for the admin, editing questions in quiz.
 */
@Repository
public class QuizDaoImpl implements QuizDao {

    @Autowired
    private SessionFactory factory;

    @Autowired
    private CategoryDao categoryDao;

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
     * Saving question to the database.
     * The question's alternative list must be updated first.
     *
     * @param question to save
     * @see Question#validateAlternatives()
     */
    @Override
    public void save(Question question) {
        Session session = factory.getCurrentSession();
        question.validateAlternatives();

        Category diff = categoryDao.findByName(question.getCategory().getName());

        if (diff == null)
            categoryDao.save(question.getCategory());
        else
            question.setCategory(diff);

        session.saveOrUpdate(question);
    }

    /**
     * Updating a question in the database.
     *
     * @param question to update
     */
    @Override
    public void update(Question question) {
        // TODO: REFACTOR!
        // nulling out id fields of the alternatives.
        question.getAlternativeList().forEach(alternative -> alternative.setId(null));

        Session session = factory.getCurrentSession();
        Question q = session.get(Question.class, question.getId());

        // If no question exists with the given id, save new
        if (q == null) {
            question.validateAlternatives();
            session.save(question);
        } else {
            q.updateAlternativeList(question.getAlternativeList());
            q.setQuestionTxt(question.getQuestionTxt());
        }
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
        Question q = session.get(Question.class, id);
        session.delete(q);
    }

    /**
     * @param question to delete
     */
    @Override
    public void delete(Question question) {
        if (question.getId() == null)
            throw new IllegalArgumentException("Question must have valid id");

        Session session = factory.getCurrentSession();
        session.delete(question);
    }

    /**
     * @param category category to fetch
     * @return list of questions with given category
     */
    @Override
    public List<Question> getByDifficulty(Category category) {
        Session session = factory.getCurrentSession();

        Category cat = categoryDao.findByName(category.getName());

        if (cat == null)
            return null;

        Query<Question> query = session.createQuery("from Question where category=:cat", Question.class);
        query.setEntity("cat", cat);

        return query.getResultList();
    }
}
