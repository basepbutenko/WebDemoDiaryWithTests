package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.Person;

import com.itvdn.springdata.webdemodiary.data.PersonInfo;
import com.itvdn.springdata.webdemodiary.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public void add(Person person) {
        personRepository.save(person);
    }
    public void deleteById(Integer id){
        personRepository.deleteById(id);
    }
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }
}
