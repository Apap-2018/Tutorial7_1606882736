package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value="dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		model.addAttribute("title","Add Car");
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method=RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car, Model model) {
		carService.addCar(car);
		model.addAttribute("title","Add Succes");
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{dealerId}", method=RequestMethod.GET)
	private String deleteCar(@PathVariable(value="dealerId") Long dealerId, Model model) {
		System.out.println(dealerId);
		model.addAttribute("title","Delete Car");
		return "deleteCar";
	}

	@RequestMapping(value = "/car/delete", method=RequestMethod.POST)
	private String deleteCarSubmit(@ModelAttribute DealerModel dealer, Model model) {
		for(CarModel car: dealer.getListCar()) {
			carService.deleteCar(car);
		}
		model.addAttribute("title","Delete Succes");
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "id") long id, Model model) {
		CarModel car = carService.getCar(id);
		model.addAttribute("car",car);
		model.addAttribute("title","Update Car");
		return "update-car";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
	private String updateCar(@PathVariable(value = "id") long id, @ModelAttribute CarModel car, Model model) {
		carService.updateCar(id, car);
		model.addAttribute("title","Update Succes");
		return "update";
	}
}