package spring.educativeprojects.kaieducativeplatform.service.Map;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.service.SphereService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SphereServiceMapImpl implements SphereService {

    Map<Integer, Sphere> sphereList;

    public SphereServiceMapImpl() {
        this.sphereList = new HashMap<>();
    }

    @Override
    public Set<Sphere> findAll() {
        return new HashSet<>(sphereList.values());
    }

    @Override
    public Sphere findById(Integer id) {
        return sphereList.get(id);
    }

    @Override
    public Sphere save(Sphere object) {
        return sphereList.put(object.getId(), object);
    }

    @Override
    public void delete(Sphere object) {
        sphereList.entrySet().removeIf(entry -> entry.equals(object));
    }

    @Override
    public void deleteById(Integer id) {
        sphereList.remove(id);
    }

    @Override
    public Sphere findByName(String name) {
        Sphere result = null;
        for(Map.Entry<Integer,Sphere> entry : sphereList.entrySet()) {
            if (entry.getValue().getName().equals(name)) result = entry.getValue();
        }
        return result;
    }
}
