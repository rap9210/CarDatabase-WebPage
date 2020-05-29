package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeCtrl {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home";
    }

    @GetMapping("/newCategory")
    public String newCatForm(Model model){
        model.addAttribute("category", new Category());
        return "newCategory";
    }

    @PostMapping("/processCatForm")
    public String processCatForm(@Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "newCategory";
        }
        else{
            categoryRepository.save(category);
            return "redirect:/";
        }
    }
    @GetMapping("/newCar")
    public String newCarForm(Model model){
            model.addAttribute("car", new Car());
            model.addAttribute("categories", categoryRepository.findAll());
            return "newCar";

    }

    @PostMapping("/processCarForm")
    public String processCar(@Valid Car car, BindingResult result){
        if(result.hasErrors()){
            return "newCar";
        }
        else{
            carRepository.save(car);
            return "redirect:/";
        }
    }

    @RequestMapping("/carRepository")
    public String allCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carRepository";
    }


    @RequestMapping("/categoryList/{id}")
    public String viewCarList(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carList";
    }



    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
        else if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }

        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {

        if (categoryRepository.existsById(id)) {
            model.addAttribute("category", categoryRepository.findById(id).get());
            return "newCategory";
        } else if (carRepository.existsById(id)) {
            model.addAttribute("car", carRepository.findById(id).get());
            model.addAttribute("categories", categoryRepository.findAll());
            return "newCar";
        }
        else{
            return "redirect:/";
        }
    }

}
