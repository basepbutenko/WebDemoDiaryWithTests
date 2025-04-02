package com.itvdn.springdata.webdemodiary.repository;

import com.itvdn.springdata.webdemodiary.data.City;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc.h2:mem:testdiary",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    @Order(1)
    @Rollback(false)
    public void testSaveCity() {
        City city = new City();
        city.setName("New York");
        cityRepository.save(city);
//        assertNotNull(city.getId());
        assertThat(city.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testFindCity() {
        City city = cityRepository.findById(1).get();
        assertThat(city.getName()).isEqualTo("New York");
    }
    @Test
    @Order(3)
    public void  testFindAllCities(){
        List<City> cities = cityRepository.findAll();
        assertThat(cities).isNotEmpty();
    }
    @Test
    @Order(4)
    @Rollback(false)
    public void testUpdateCity() {
        City city = cityRepository.findById(1).get();
        city.setName("Abbotsford, Canada");
        City updatedCity = cityRepository.save(city);
        assertThat(updatedCity.getName()).isEqualTo("Abbotsford, Canada");
    }
    @Test
    @Order(5)
    @Rollback(false)
    public void testDeleteCity() {
        City city = cityRepository.findById(1).get();
        cityRepository.delete(city);
        assertThat(cityRepository.findAll()).isEmpty();
    }


}