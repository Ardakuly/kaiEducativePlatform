package spring.educativeprojects.kaieducativeplatform.exceptions;

public class UserPasswordNotValidException extends RuntimeException{

    public UserPasswordNotValidException(String message) {
        super(message);
    }
}
