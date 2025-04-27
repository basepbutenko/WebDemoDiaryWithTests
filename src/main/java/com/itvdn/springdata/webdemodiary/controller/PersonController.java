package com.itvdn.springdata.webdemodiary.controller;

import com.itvdn.springdata.webdemodiary.data.City;
import com.itvdn.springdata.webdemodiary.data.Meeting;
import com.itvdn.springdata.webdemodiary.data.Person;

import com.itvdn.springdata.webdemodiary.data.PersonInfo;
import com.itvdn.springdata.webdemodiary.service.CityService;
import com.itvdn.springdata.webdemodiary.service.MeetingService;
import com.itvdn.springdata.webdemodiary.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private final MeetingService meetingService;
    private PersonService personService;
    private CityService cityService;

    @GetMapping
    public String showAllPeople(Model model) {
        List<Person> people = personService.findAll();
        model.addAttribute("people", people);
        model.addAttribute("cities", cityService.findAll());
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
        model.addAttribute("info", "Find by name: " + name);
        return "people_view";
    }

    @PostMapping("/by_first_letters")
    public String findByFirstLetter(Model model, @RequestParam String firstLetters) {
        List<PersonInfo> people = personService.findByFirstLetters(firstLetters);
        model.addAttribute("people", people);
        model.addAttribute("info", "Find by First Letters: " + firstLetters);
        return "people_view";

    }

    @PostMapping("/by_cities")
    public String findByCities(Model model, @RequestParam("cities") List<Integer> cityIds) {
        List<PersonInfo> people = personService.findByCities(cityIds);
        model.addAttribute("people", people);
        model.addAttribute("info", "Find by Cities ");
        return "people_view";
    }

    @PostMapping("/add")
    public String addPerson(
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam LocalDate bday,
            @RequestParam String phone,
            @RequestParam String email,

            @RequestParam String address,
            @RequestParam int cityid) {
        City city = cityService.findById(cityid);
        Person p = new Person(fname, lname, bday, phone, email, address, city);
        personService.add(p);
        return "redirect:/people";
    }

    @GetMapping({"/delete", "/delete/"})
    public String deletePerson(@RequestParam int id) {
        personService.deleteById(id);
        return "redirect:/people";
    }

    @GetMapping({"/meetings", "/meetings/"})
    public String showMeetings(Model model, @RequestParam(required = false) Integer id) {
        if (id == null) {
            return "redirect:/people"; // или можно вывести страницу с ошибкой
        }
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("available", meetingService.findAvailableMeetings(id));
            return "person_meetings";
        } else {
            return "redirect:/people";
        }
    }

    @GetMapping("/meetings/participate")
    public String participateMeetings(@RequestParam int pid, @RequestParam int mid) {
        meetingService.addParticipation(pid, mid);
        return "redirect:/people/meetings?id=" + pid ;
    }

    @GetMapping("/meetings/cancel")
    public String cancelMeetings(@RequestParam int pid, @RequestParam int mid) {
        meetingService.cancelParticipation(pid, mid);
        return "redirect:/people/meetings?id=" + pid ;
    }
}


