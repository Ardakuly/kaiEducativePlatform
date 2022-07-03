package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;

import java.util.Set;

public class ValidatorCourse {

    public static boolean emptyCoursesValidator(Set<CourseDTO> coursesDTO) {

           if (coursesDTO.size() == 0) return true;

           return false;
    }

    public static boolean nullCourseValidator(CourseDTO coursesDTO) {

        if (coursesDTO == null) return true;

        return false;
    }

    public static boolean idCourseEmptyValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }

    public static boolean idCourseValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameCourseEmptyValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameCourseValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122)))  {
                return true;
            }
        }



        return false;
    }


}
