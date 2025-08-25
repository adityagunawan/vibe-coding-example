package com.vibecoding.controller;

import com.vibecoding.model.Country;
import com.vibecoding.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String listCountries(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        return "country-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("country", new Country());
        return "country-form";
    }

    @PostMapping
    public String createCountry(@ModelAttribute Country country) {
        countryService.saveCountry(country);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Country country = countryService.getCountryById(id);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "country-form";
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable Long id, @ModelAttribute Country country) {
        Country existing = countryService.getCountryById(id);
        if (existing == null) {
            return "redirect:/countries";
        }
        existing.setCountryName(country.getCountryName());
        countryService.saveCountry(existing);
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }
}
