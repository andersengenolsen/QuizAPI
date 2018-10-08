package springquiz.dao;

import springquiz.entity.Category;
import springquiz.entity.Question;

import java.util.List;

public interface QuizDao extends Dao<Question> {
    List<Question> getByDifficulty(Category category);
}
