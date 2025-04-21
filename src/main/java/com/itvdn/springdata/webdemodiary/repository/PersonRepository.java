package com.itvdn.springdata.webdemodiary.repository;

import com.itvdn.springdata.webdemodiary.data.Person;
import com.itvdn.springdata.webdemodiary.data.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<PersonInfo> findByFirstName(String firstName);

    List<PersonInfo> findByFirstNameStartsWith(String firstName);

    List<PersonInfo> findByCity_IdIn(Collection<Integer> ids);


//    @Query("select p from Person p where p.firstName = ?1")
//    List<PersonInfo> findPeopleByName(String firstName);

    @Query("select p from Person p where p.firstName like concat(?1, '%')")
    List<Person> findPeopleByFirstLetters(String firstName);

    @Query("select p from Person p where p.city.id in ?1")
    List<Person>findByCities( Collection<Integer> cityIds);



}