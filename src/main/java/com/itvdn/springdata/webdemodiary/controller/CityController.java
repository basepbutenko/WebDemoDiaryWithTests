package com.itvdn.springdata.webdemodiary.controller;

import com.itvdn.springdata.webdemodiary.data.City;
import com.itvdn.springdata.webdemodiary.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping
    public String showCities(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "cities";
    }

    @PostMapping("/add_city")
    public String addCity(@RequestParam String city) {
        cityService.add(new City(city));
        return "redirect:/cities";
    }
    @GetMapping("/delete")
    public String deleteCity(@RequestParam(name = "id") Integer cityId) {
        cityService.delete(cityId);
        return "redirect:/cities";
    }

}
