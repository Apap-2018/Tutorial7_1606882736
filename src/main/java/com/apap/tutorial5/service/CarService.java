package com.apap.tutorial5.service;

import java.util.Optional;

import com.apap.tutorial5.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	void deleteCar(CarModel car);
	Optional<CarModel> getCarDetailById(Long id);
	CarModel getCar(Long id);
	void updateCar(long id, CarModel newCar);
}
