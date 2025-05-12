package com.example.ajax.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContinentMapper {
	List<Map<String, Object>> selectContinentList();

	List<Map<String, Object>> selectCountryList(String continentNo);
	
	List<Map<String, Object>> selectCityList(String countryNo);
}
