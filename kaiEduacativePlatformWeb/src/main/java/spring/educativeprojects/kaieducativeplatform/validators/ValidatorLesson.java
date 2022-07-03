package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import java.util.Set;

public class ValidatorLesson {

    public static boolean lessonIsEmptyValidator(Set<LessonDTO> lesson) {

        if (lesson.size() == 0) return true;

        return false;
    }

    public static boolean idLessonEmptyValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }
    public static boolean idLessonValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameLessonEmptyValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameLessonValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122)))  {
                return true;
            }
        }

        return false;
    }

    public static boolean nullLessonValidator(LessonDTO lessonDTO) {
        return (lessonDTO == null) ? true : false;
    }

}
