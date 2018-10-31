package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PostMapping()
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		return carService.getCarDetailById(carId);
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model){
		return carService.viewAllCar();
	}
	
	@DeleteMapping(value = "/{carId}")
	private String deleteDealer(@RequestParam ("carId") long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId);
		carService.deleteCar(car);
		return "car has been deleted";
	}
	
	@PutMapping(value = "/{carId}")
	private String updateCarSubmit(
			@PathVariable (value = "carId") long carId,
			@RequestParam ("brand") String brand,
			@RequestParam ("type") String type,
			@RequestParam ("price") String price,
			@RequestParam ("amount") String amount,
			@RequestParam ("dealerID") String dealerID) {
		CarModel car = (CarModel)carService.getCarDetailById(carId);
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(Long.parseLong(dealerID)).get();
		if(car.equals(null)) {
			return "Couldn't find your dealer";
		}
		car.setBrand(brand);
		car.setType(type);
		car.setPrice(Long.parseLong(price));
		car.setAmount(Integer.parseInt(amount));
		car.setId(carId);
		car.setDealer(dealer);
		carService.updateCar(carId, car);
		return "update success";
	}
}