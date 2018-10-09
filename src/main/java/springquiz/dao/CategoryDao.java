package springquiz.dao;

import springquiz.entity.Category;

/**
 * DAO against {@link Category} entity.
 */
public interface CategoryDao extends Dao<Category> {
    Category findByName(String name);
}
