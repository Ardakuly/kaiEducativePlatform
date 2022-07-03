package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;

import java.util.Set;

public class ValidatorModule {

    public static boolean moduleIsEmptyValidator(Set<ModuleDTO> modules) {

        if (modules.size() == 0) return true;

        return false;
    }

    public static boolean moduleIsNullValidator(ModuleDTO module) {

        if (module == null) return true;

        return false;
    }

    public static boolean idEmptyModuleValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }

    public static boolean idModuleValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nameIsEmptyModuleValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameModuleValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122)))  {
                return true;
            }
        }

        return false;
    }
}
