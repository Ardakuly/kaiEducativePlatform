package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;

import java.util.Set;

public class ValidateAuthor {


    public static boolean allAuthorsIsEmpty(Set<AuthorDTO> allAuthors) {

        return (allAuthors.size() == 0) ? true : false;
     }


    public static boolean authorIsNull(AuthorDTO authorDTO) {

        return (authorDTO == null) ? true : false;
    }

    public static boolean idEmptyAuthorValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }

    public static boolean idAuthorValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameIsEmptyAuthorValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameAuthorValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122) || name.charAt(i) == ' '))  {
                return true;
            }
        }

        return false;
    }

}
