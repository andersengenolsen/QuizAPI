package springquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for exceptions when
 * consuming the API.
 */
@ControllerAdvice
public class QuestionRestExceptionHandler {

    /**
     * @param e {@link QuestionNotFoundException}
     * @return ResponseEntity with {@link ApiErrorResponse}, 404
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(
            QuestionNotFoundException e) {

        ApiErrorResponse err = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handling any exception.
     *
     * @param e any {@link Exception}
     * @return ResponseEntity with {@link ApiErrorResponse}, 400
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception e) {
        ApiErrorResponse err = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
}
