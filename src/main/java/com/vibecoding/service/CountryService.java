package com.vibecoding.service;

import com.vibecoding.model.Country;
import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryById(Long id);
    Country saveCountry(Country country);
    void deleteCountry(Long id);
}
