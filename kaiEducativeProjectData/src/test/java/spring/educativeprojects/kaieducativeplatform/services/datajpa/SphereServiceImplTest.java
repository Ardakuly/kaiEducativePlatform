package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.repositories.SphereRepository;
import spring.educativeprojects.kaieducativeplatform.services.SphereService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static spring.educativeprojects.kaieducativeplatform.entities.Sphere.*;

@ExtendWith(MockitoExtension.class)
class SphereServiceImplTest {

    @Mock
    SphereRepository repositorySphere;

    @InjectMocks
    SphereServiceImpl serviceSphereImpl;

    Set<Sphere> spheres;
    Sphere sphere1;
    Sphere sphere2;

    @BeforeEach
    void setUp() {
        spheres = new HashSet<>();
        sphere1 = new Sphere();
        sphere1.setId(1);
        sphere1.setName("ICT");

        sphere2 = new Sphere();
        sphere2.setId(2);
        sphere2.setName("Programming");
    }

//    @Test
//    void findAll() {
//        spheres.add(sphere1);
//        spheres.add(sphere2);
//
//        when(repositorySphere.findAll()).thenReturn(spheres);
//
//        Set<Sphere> returnSpheres = serviceSphereImpl.findAll();
//
//        assertEquals(2, returnSpheres.size());
//
//
//    }
//
//    @Test
//    void findById() {
//
//        when(repositorySphere.findById(any())).thenReturn(Optional.ofNullable(sphere1));
//        Integer id = sphere1.getId();
//        Sphere sphereReturned = serviceSphereImpl.findById(id);
//
//        assertEquals(id, sphereReturned.getId());
//
//        verify(repositorySphere, times(1)).findById(any());
//
//
//
//    }
//
//    @Test
//    void findByIdNull() {
//
//        when(repositorySphere.findById(any())).thenReturn(Optional.empty());
//        Integer id = sphere1.getId();
//        Sphere sphereReturned = serviceSphereImpl.findById(id);
//
//        assertNull(serviceSphereImpl.findById(id));
//
//
//    }
//
//    @Test
//    void findByName() {
//        when(repositorySphere.findByName(any())).thenReturn(Optional.ofNullable(sphere2));
//
//        Sphere returnedSphere = serviceSphereImpl.findByName("Programming");
//
//        assertEquals("Programming", returnedSphere.getName());
//        verify(repositorySphere).findByName(any());
//
//
//    }
//
//    @Test
//    void save() {
//        serviceSphereImpl.save(sphere1);
//
//        verify(repositorySphere).save(any());
//    }
//
//    @Test
//    void delete() {
//        serviceSphereImpl.delete(sphere1);
//
//        verify(repositorySphere).delete(any());
//    }
//
//    @Test
//    void deleteById() {
//        serviceSphereImpl.deleteById(sphere1.getId());
//
//        verify(repositorySphere).deleteById(any());
//    }
}