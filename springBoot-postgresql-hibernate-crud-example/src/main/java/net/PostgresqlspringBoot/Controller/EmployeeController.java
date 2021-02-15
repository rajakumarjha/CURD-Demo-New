package net.PostgresqlspringBoot.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.PostgresqlspringBoot.Exception.ResourceNotFoundException;
import net.PostgresqlspringBoot.Model.Employee;
import net.PostgresqlspringBoot.Repository.EmployeeRepository;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository EmpRepo;
	
	//get employee
	
	@GetMapping("employee")
	public List<Employee> getAllEmployee(){
		return this.EmpRepo.findAll();
	}
	
	
	
	//get employee by id
	
	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable (value="id") long employeeId) throws ResourceNotFoundException {
		
		
		 Employee employee=EmpRepo.findById(employeeId).
		      orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ employeeId+""));
		
		return ResponseEntity.ok().body(employee);}
				
	
	//save employee
	
	@PostMapping("employee")
	public Employee CreateEmployee(@RequestBody Employee employee) {
		return this.EmpRepo.save(employee);
	}
	//update employee
	
	@PutMapping("employee/{id}")
	public ResponseEntity<Employee> UpdateEmployee(@PathVariable (value="id") Long employeeId,
			@Validated @RequestBody Employee employeDetail) throws ResourceNotFoundException{
		
		Employee employee=EmpRepo.findById(employeeId).
			      orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ employeeId+""));	
		employee.setEmail(employeDetail.getEmail());
		employee.setFirstName(employeDetail.getFirstName());
		employee.setLastName(employeDetail.getLastName());
		
		return ResponseEntity.ok(this.EmpRepo.save(employee));
			
	}
	
	//delete employee
	@DeleteMapping("employee/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable (value ="id") Long employeeID) throws ResourceNotFoundException{
		Employee employee=EmpRepo.findById(employeeID).
			      orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ employeeID+""));	
		
		this.EmpRepo.delete(employee);
		
		Map<String, Boolean>response =new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
}
