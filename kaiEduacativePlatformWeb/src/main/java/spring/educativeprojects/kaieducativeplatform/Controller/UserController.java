package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.entities.User;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.UserEmailNotValidException;
import spring.educativeprojects.kaieducativeplatform.exceptions.UserPasswordNotValidException;
import spring.educativeprojects.kaieducativeplatform.services.datajpa.UserRegistration;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorUser;

@Controller
@RequestMapping("users/v1")
public class UserController {

    public final UserRegistration userRegistration;

    @Autowired
    public UserController(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUpUser(@RequestBody User user) {

        if (!ValidatorUser.validateEmail(user.getEmail())) {
            throw new UserEmailNotValidException("Введенная почта являеться недействительной");
        }else if (!ValidatorUser.validatePassword(user.getPassword())) {
            throw new UserPasswordNotValidException("Введенный пароль являеться недействительным");
        }else if (ValidatorUser.validateUserName(user.getFirstName())) {
            throw new NameFormatException("Введенныое имя являться недействительной");
        }else if (ValidatorUser.validateUserName(user.getSecondName())) {
            throw new NameFormatException("Введенныое фамилия являться недействительной");
        }

        return new ResponseEntity<String>(userRegistration.register(user), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<String>(userRegistration.confirm(token), HttpStatus.OK);
    }

}
