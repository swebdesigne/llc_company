package com.company.lls.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "hello")
public class Application {
	@GetMapping(value = "/{firstName}")
	public String helloGet(
			@PathVariable("firstName") String firstName,
			@RequestParam("lastName") String lastName
	) {
		return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
	}

	@PostMapping(consumes = "application/json")
	public String helloPost(@RequestBody Map<String, String> requestParams) {
		String firstName = requestParams.get("firstName");
		String lastName = requestParams.get("lastName");
		return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
	}
}

