package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class SessionController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/checkin")
	public ResponseEntity<?> loginOrSignup(@RequestBody UserEntity requestUser) {
		Map<String, Object> response = new HashMap<>();

		return userRepository.findByEmail(requestUser.getEmail()).map(existingUser -> {
			// Login logic
			if (existingUser.getPassword().equals(requestUser.getPassword())) {
				response.put("status", "success");
				response.put("message", "Login successful");
				response.put("user", existingUser);
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "error");
				response.put("message", "Invalid password");
				return ResponseEntity.badRequest().body(response);
			}
		}).orElseGet(() -> {
			// Signup logic
			UserEntity newUser = new UserEntity();
			newUser.setFirstName(requestUser.getFirstName());
			newUser.setEmail(requestUser.getEmail());
			newUser.setPassword(requestUser.getPassword());

			UserEntity savedUser = userRepository.save(newUser);

			response.put("status", "success");
			response.put("message", "Signup successful");
			response.put("user", savedUser);
			return ResponseEntity.ok(response);
		});
	}
}
