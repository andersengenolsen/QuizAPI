package springquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springquiz.entity.Question;
import springquiz.exception.QuestionNotFoundException;
import springquiz.service.Service;

import java.util.List;

/**
 * Controller for access against "/api/admin", defining admin endpoints.
 * Operations for editing quizzes..
 *
 * @author Anders Engen Olsen
 */
@RestController
@RequestMapping("/api/admin")
public class QuizController {

    @Autowired
    private Service<Question> service;

    /**
     * @return List of all questions in database
     */
    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return service.getAll();
    }

    /**
     * @param questionId id of question
     * @return Question with given id
     * @throws QuestionNotFoundException If question is null
     */
    @GetMapping("/questions/{questionId}")
    public Question getQuestion(@PathVariable int questionId) {
        Question question = service.get(questionId);

        if (question == null)
            throw new QuestionNotFoundException("Question not found - " + questionId);

        return service.get(questionId);
    }

    /**
     * @param question question to save to the database
     * @return saved question with valid id
     */
    @PostMapping("/questions")
    public Question addQuestion(@RequestBody Question question) {
        // Setting id to null, to insert regardless of the id.
        // Avoiding update in database on POST requests
        question.setId(null);
        service.save(question);
        return question;
    }

    /**
     * Updating or creating a question
     *
     * @param question question to update
     * @return updated question
     */
    @PutMapping("/questions")
    public Question updateQuestion(@RequestBody Question question) {
        service.update(question);
        return question;
    }

    /**
     * Deleting question with given id
     *
     * @param questionId id of question to delete
     * @return 200 OK
     * @throws {@link QuestionNotFoundException} if no question with given id
     */
    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable int questionId) {
        Question q = service.get(questionId);

        if (q == null)
            throw new QuestionNotFoundException("Question not found, id - " + questionId);

        service.delete(q);

        return "Deleted question id - " + questionId;
    }


}
