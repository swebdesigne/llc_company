package com.llcgrowth.license.controller;

import com.llcgrowth.license.model.License;
import com.llcgrowth.license.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/organisation/{organisationId}/license")
public class LicenseController {
	private LicenseService licenseService;

	@GetMapping(value = "/{licenseId}")
	public ResponseEntity<License> getLicense(
			@PathVariable("organisationId") String organisationId,
			@PathVariable("licenseId") String licenseId
	) {
		return ResponseEntity.ok(licenseService.getLicense(licenseId, organisationId));
	}

	@PutMapping
	public ResponseEntity<String> updateLicense(
			@PathVariable("organisationId") String organisationId,
			@RequestBody License license
	) {
		return ResponseEntity.ok(licenseService.updateLicense(license, organisationId));
	}

	@PostMapping
	public ResponseEntity<String> createLicense(
			@PathVariable("organisationId") String organisationId,
			@RequestBody License license,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale
	) {
		return ResponseEntity.ok(licenseService.createLicense(license, organisationId, locale));
	}

	@DeleteMapping(value = "/{licenseId}")
	public ResponseEntity<String> deleteLicense(
			@PathVariable("organisationId") String organisationId,
			@PathVariable("licenseId") String licenseId
	) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organisationId));
	}
}
