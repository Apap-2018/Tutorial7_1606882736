package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	CarModel getCarDetailById(Long id);
	CarModel getCar(Long id);
	void updateCar(long id, CarModel newCar);
	List<CarModel> viewAllCar();
}
