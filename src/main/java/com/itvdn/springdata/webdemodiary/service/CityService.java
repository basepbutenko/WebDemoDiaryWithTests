package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.City;
import com.itvdn.springdata.webdemodiary.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }
    public void add(City city) {
        cityRepository.save(city);
    }

    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
