package springquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springquiz.dao.CategoryDao;
import springquiz.entity.Category;

import java.util.List;

/**
 * Service for methods against the difficulty entity.
 */
@Service
public class CategoryServiceImpl implements springquiz.service.Service<Category> {

    @Autowired
    private CategoryDao dao;

    @Override
    @Transactional
    public List<Category> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void save(Category category) {
        dao.save(category);
    }

    @Override
    @Transactional
    public Category get(int id) {
        return dao.get(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    @Transactional
    public void delete(Category T) {
        dao.delete(T);
    }

    @Override
    @Transactional
    public void update(Category category) {
        dao.update(category);
    }
}
