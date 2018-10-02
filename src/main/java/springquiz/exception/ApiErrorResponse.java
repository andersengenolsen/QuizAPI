package springquiz.exception;

/**
 * Class defining custom error responses.
 */
public class ApiErrorResponse {

    /**
     * HTTP status code
     */
    private int status;

    /**
     * Error message
     */
    private String message;

    /**
     * Timestamp
     */
    private long timestamp;

    /* -- CONSTRUCTORS, GETTERS & SETTERS -- */

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
