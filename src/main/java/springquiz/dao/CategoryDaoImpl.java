package springquiz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springquiz.entity.Category;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
// TODO: Implement DifficultyDAO endpoints
class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory factory;


    /**
     * Returning difficulty by name
     *
     * @param name Name of difficulty category
     * @return Category, null if not found
     */
    @Override
    public Category findByName(String name) {
        Session session = factory.getCurrentSession();

        Query<Category> query = session.createQuery("from Category where name=:name", Category.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        } catch (NoResultException err) {
            return null;
        }
    }

    /**
     * @param category to save to database.
     */
    @Override
    public void save(Category category) {
        Session session = factory.getCurrentSession();
        session.save(category);
    }

    /**
     * @return All difficulty categories in the database.
     */
    @Override
    public List<Category> getAll() {
        Session session = factory.getCurrentSession();
        Query<Category> query = session.createQuery("from Category ", Category.class);
        return query.getResultList();
    }


    // TODO: Implement DB calls
    @Override
    public void update(Category category) {

    }

    @Override
    public Category get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Category category) {

    }
}
