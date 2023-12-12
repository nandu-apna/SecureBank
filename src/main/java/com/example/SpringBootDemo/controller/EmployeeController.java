package com.example.SpringBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SpringBootDemo.dao.EmployeeDao;
import com.example.SpringBootDemo.model.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeDao dao;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/add")
	public String add() {
		
		return "register";
		
	}
	
	@PostMapping("/addEmp")
	public String addEmp(Model model,@RequestParam String id, @RequestParam String name, @RequestParam String designation, @RequestParam String salary) {
		 
		 Employee emp=new Employee(Integer.parseInt(id),name,designation,Float.parseFloat(salary));
		 dao.save(emp);
		 model.addAttribute("msg","Employee added Successfully!");
	  	 return "register";
	}
	
	
	
}
