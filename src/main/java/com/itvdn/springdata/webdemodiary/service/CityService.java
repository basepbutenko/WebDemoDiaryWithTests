package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.City;
import com.itvdn.springdata.webdemodiary.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
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

    public City findById(Integer cityId) {
        return cityRepository.findById(cityId).orElse (null);
    }

    public void updateCity(Integer id, String name) {
         cityRepository.findById(id).ifPresent(city -> {
             city.setName(name);
             cityRepository.save(city);
         });
    }

    @Transactional
    public void addAll(List<String> citiesList) {
        citiesList.stream().map(String::trim).filter(s->!s.isBlank()).map(City::new).forEach(cityRepository::save);
    }
}
