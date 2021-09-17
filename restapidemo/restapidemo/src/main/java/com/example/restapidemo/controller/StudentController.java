package com.example.restapidemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapidemo.model.*;
import com.example.restapidemo.view.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {
	@Autowired
	private Studentview stud;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return stud.findAll();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
			{
		Optional<Student> optionalEntity = stud.findById(studentId);
		Student student=optionalEntity.get();
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/students")
	public Student createStudnet(@Validated @RequestBody Student student) {
		return stud.save(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
			@Validated @RequestBody Student studentDetails) {
		Optional<Student> optionalEntity = stud.findById(studentId);
		Student student =optionalEntity.get();

		student.setEmailId(studentDetails.getEmailId());
		student.setLastName(studentDetails.getLastName());
		student.setFirstName(studentDetails.getFirstName());
		final Student updatedStudent = stud.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/students/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
			 {
		Optional<Student> optionalEntity = stud.findById(studentId);
		Student student =optionalEntity.get();

		stud.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}