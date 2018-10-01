package springquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springquiz.entity.Question;
import springquiz.service.Service;

import java.util.List;

/**
 * Controller for access against "/api/admin".
 * Operations for editing quizzes.
 */
@RestController
@RequestMapping("/api/admin")
public class QuizController {

    @Autowired
    private Service<Question> service;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return service.getAll();
    }
}
