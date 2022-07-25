package spring.educativeprojects.kaieducativeplatform.exceptions;

public class UserEmailNotValidException extends RuntimeException{

    public UserEmailNotValidException(String message) {
        super(message);
    }
}
