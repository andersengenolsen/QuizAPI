package springquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springquiz.dao.DifficultyDao;
import springquiz.entity.Category;

import java.util.List;

/**
 * Service for methods against the difficulty entity.
 */
@Service
public class DifficultyServiceImpl implements springquiz.service.Service<Category> {

    @Autowired
    private DifficultyDao dao;

    @Override
    @Transactional
    public List<Category> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Category category) {
        dao.save(category);
    }

    @Override
    public Category get(int id) {
        return dao.get(id);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public void delete(Category T) {
        dao.delete(T);
    }

    @Override
    public void update(Category category) {
        dao.update(category);
    }
}
