package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.Person;
import com.itvdn.springdata.webdemodiary.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;


    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public List<Person> findByName(String name) {
        return personRepository.findPeopleByName(name);

    }

    public List<Person> findByFirstLetters(String firstLetters) {
        return personRepository.findPeopleByFirstLetters(firstLetters);
    }

    public List<Person> findByCities(List<Integer> cityIds) {
        return personRepository.findByCities(cityIds);
    }
}
