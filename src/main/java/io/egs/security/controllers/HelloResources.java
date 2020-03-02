package io.egs.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResources {

	@GetMapping("/hello")
	public String helloResource() {
		return "Hello from Resource API";
	}
}
