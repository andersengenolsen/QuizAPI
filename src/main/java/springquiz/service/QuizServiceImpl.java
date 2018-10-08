package springquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springquiz.dao.QuizDao;
import springquiz.entity.Category;
import springquiz.entity.Question;

import java.util.List;

/**
 * Service for performing admin operations agains the quiz database.
 */
@org.springframework.stereotype.Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao dao;

    /**
     * @return all questions
     */
    @Override
    @Transactional
    public List<Question> getAll() {
        return dao.getAll();
    }

    /**
     * @param question to save
     */
    @Override
    @Transactional
    public void save(Question question) {
        dao.save(question);
    }

    /**
     * @param id id of question
     * @return question with id
     */
    @Override
    @Transactional
    public Question get(int id) {
        return dao.get(id);
    }

    /**
     * @param id to delete
     */
    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * @param T object to delete
     */
    @Override
    @Transactional
    public void delete(Question T) {
        dao.delete(T);
    }

    @Override
    @Transactional
    public void update(Question question) {
        dao.update(question);
    }

    @Override
    @Transactional
    public List<Question> getByDifficulty(Category category) {
        return dao.getByDifficulty(category);
    }
}
