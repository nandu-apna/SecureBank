package com.example.SpringBootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/show")
	public String showEmployee(Model model) {
		
		List<Employee> employees = dao.findAll();
		model.addAttribute("employeeList", employees);
		return "info";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable int id, Model model) {
		
		Employee emp=dao.findById(id).orElse(null);
		
		model.addAttribute("emp",emp);
	    return "register";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id, Model model) {
		dao.deleteById(id);
 
		return "redirect:/show";
	}
	
}
