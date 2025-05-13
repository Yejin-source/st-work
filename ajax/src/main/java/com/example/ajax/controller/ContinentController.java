package com.example.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ajax.mapper.CityMapper;
import com.example.ajax.mapper.ContinentMapper;
import com.example.ajax.mapper.CountryMapper;

@Controller
public class ContinentController {
	@Autowired ContinentMapper continentMapper;
	@Autowired CountryMapper countryMapper;
	@Autowired CityMapper cityMapper;
	
	@GetMapping({"/", "/continentList"})
	public String continentList(
			@RequestParam(value = "continent", required = false) Integer continentNo,
			@RequestParam(value = "country", required = false) Integer countryNo,
			Model model) {
		
		model.addAttribute("continentList", continentMapper.selectContinentList());
		
		if(continentNo != null) {
			model.addAttribute("countryList", countryMapper.selectCountryList(continentNo));
		}

		if(countryNo != null) {
			model.addAttribute("cityList", cityMapper.selectCityList(countryNo));
		}
		
		return "continentList";
	}
}
