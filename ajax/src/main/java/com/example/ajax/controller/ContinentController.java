package com.example.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ajax.mapper.ContinentMapper;

@Controller
public class ContinentController {
	@Autowired ContinentMapper continentMapper;
	
	@GetMapping({"/", "/continentList"})
	public String continentList(
			@RequestParam(value = "continent", required = false) String continentNo,
			@RequestParam(value = "country", required = false) String countryNo,
			Model model) {
		
		model.addAttribute("continentList", continentMapper.selectContinentList());
		
		if(continentNo != null && !continentNo.isEmpty()) {
			model.addAttribute("countryList", continentMapper.selectCountryList(continentNo));
		}

		if(countryNo != null && !countryNo.isEmpty()) {
			model.addAttribute("cityList", continentMapper.selectCityList(countryNo));
		}
		
		return "continentList";
	}
}
