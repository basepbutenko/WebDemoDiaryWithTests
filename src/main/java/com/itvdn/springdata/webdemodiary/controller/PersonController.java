package com.itvdn.springdata.webdemodiary.controller;

import com.itvdn.springdata.webdemodiary.data.Person;

import com.itvdn.springdata.webdemodiary.data.PersonInfo;
import com.itvdn.springdata.webdemodiary.service.CityService;
import com.itvdn.springdata.webdemodiary.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private PersonService personService;
    private CityService cityService;

    @GetMapping
    public String showAllPeople(Model model) {
        List<Person> people = personService.findAll();
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/find")
    public String showSeekPage(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "people_find";
    }

    @PostMapping("/by_name")
    public String findByName(Model model, @RequestParam String name) {
        List<PersonInfo> people = personService.findByName(name);
        model.addAttribute("people", people);
        model.addAttribute("info","Find by name: " + name );
        return "people_view";
    }

    @PostMapping("/by_first_letters")
    public String findByFirstLetter(Model model, @RequestParam String firstLetters) {
        List<PersonInfo> people = personService.findByFirstLetters(firstLetters);
        model.addAttribute("people", people);
        model.addAttribute("info","Find by First Letters: " + firstLetters);
        return "people_view";

    }

    @PostMapping("/by_cities")
    public String findByCities(Model model, @RequestParam ("cities") List<Integer> cityIds) {
        List<PersonInfo>people=personService.findByCities(cityIds);
        model.addAttribute("people", people);
        model.addAttribute("info","Find by Cities ");
        return "people_view" +
               "" +
               "";

    }


}
