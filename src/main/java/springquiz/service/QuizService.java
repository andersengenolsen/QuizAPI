package springquiz.service;

import springquiz.entity.Category;
import springquiz.entity.Question;

import java.util.List;

public interface QuizService extends Service<Question> {
    List<Question> getByDifficulty(Category category);
}
