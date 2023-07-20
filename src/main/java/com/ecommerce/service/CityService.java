package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.City;
import com.ecommerce.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	


	public List<City> findAll() {
		// TODO Auto-generated method stub
		return cityRepository.findAll();
	}

}
