package com.itvdn.springdata.webdemodiary.repository;

import com.itvdn.springdata.webdemodiary.data.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}