package by.stepovoy.exception;

public class DuplicateUserNameException extends RuntimeException {
    private String message;

    public DuplicateUserNameException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
