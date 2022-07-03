package spring.educativeprojects.kaieducativeplatform.validators;

import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;

import java.util.Set;

public class ValidatorSphere {

    public static boolean nameSphereEmptyValidator(SphereDTO sphereDTO) {
        if (sphereDTO.getName().length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameSphereValidator(SphereDTO sphereDTO) {

        for (int i = 0; i < sphereDTO.getName().length(); i++) {
            if (!((sphereDTO.getName().charAt(i) >= 65 && sphereDTO.getName().charAt(i) <= 90) ||
                    (sphereDTO.getName().charAt(i) >= 97 && sphereDTO.getName().charAt(i) <= 122)))  {
                return true;
            }
        }

        return false;
    }

    public static boolean nameSphereEmptyValidator(String name) {
        if (name.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean nameSphereValidator(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) ||
                    (name.charAt(i) >= 97 && name.charAt(i) <= 122)))  {
                return true;
            }
        }

        return false;
    }

    public static boolean nullSphereValidor(SphereDTO sphereDTO) {

        if (sphereDTO == null) return true;

        return false;
    }

    public static boolean emptySpheresValidator(Set<SphereDTO> spheresDTO) {
        if (spheresDTO.size() == 0) return true;

        return false;
    }


    public static boolean idSphereEmptyValidator(String id) {
        if (id.length() == 0) return true;

        return false;
    }
    public static boolean idSphereValidator(String id) {

        for (int i = 0; i < id.length(); i++) {
            if (!(id.charAt(i) >= 48 && id.charAt(i) <= 57)) {
                return true;
            }
        }
        return false;
    }

}

