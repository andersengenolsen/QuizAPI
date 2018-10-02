package springquiz.exception;

/**
 * Custom exception, thrown when Question not found.
 */
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
        super();
    }

    public QuestionNotFoundException(String s) {
        super(s);
    }

    public QuestionNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
