package com.congdinh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(
        @RequestParam(name = "name", required = false, defaultValue = "World") String name, 
        @RequestParam(name = "age", required = false, defaultValue = "10") int age, 
        Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "home/index";
    }

    @GetMapping("/about/{name}/{age}")
    public String about(
        @PathVariable(name = "name", required = false) String name,
        @PathVariable(name = "age", required = false) int age,
         Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "home/about";
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView("home/contact");
        modelAndView.addObject("name", "Cong Dinh");
        return modelAndView;
    }
}
