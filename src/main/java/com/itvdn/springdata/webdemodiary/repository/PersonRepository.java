package com.itvdn.springdata.webdemodiary.repository;

import com.itvdn.springdata.webdemodiary.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where p.firstName = ?1")
    List<Person> findPeopleByName(String firstName);

    @Query("select p from Person p where p.firstName like concat(?1, '%')")
    List<Person> findPeopleByFirstLetters(String firstName);

    @Query("select p from Person p where p.city.id in ?1")
    List<Person>findByCities( Collection<Integer> cityIds);



}