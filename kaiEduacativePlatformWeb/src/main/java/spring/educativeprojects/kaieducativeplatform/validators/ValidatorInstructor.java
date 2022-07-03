package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;

import java.util.Set;

public class ValidatorInstructor {

    public static boolean allInstructorIsEmpty(Set<InstructorDTO> allInstructors) {

        return (allInstructors.size() == 0) ? true : false;
    }


    public static boolean instructorIsNull(InstructorDTO instructorDTO) {

        return (instructorDTO == null) ? true : false;
    }

    public static boolean idEmptyInstructorValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }

    public static boolean idInstructorValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameIsEmptyInstructorValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameInstructorValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122) || name.charAt(i) == ' '))  {
                return true;
            }
        }

        return false;
    }

}
