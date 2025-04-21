package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.Person;

import com.itvdn.springdata.webdemodiary.data.PersonInfo;
import com.itvdn.springdata.webdemodiary.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;


    public List<Person> findAll() {
        return personRepository.findAll(Sort.by("firstName").descending());
    }

    public List<PersonInfo> findByName(String name) {
        return personRepository.findByFirstName(name);
    }

    public List<PersonInfo> findByFirstLetters(String firstLetters) {
        return personRepository.findByFirstNameStartsWith(firstLetters);
    }

    public List<PersonInfo> findByCities(List<Integer> cityIds) {
        return personRepository.findByCity_IdIn(cityIds);
    }
}
