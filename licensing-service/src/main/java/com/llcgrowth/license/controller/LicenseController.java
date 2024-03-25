package com.llcgrowth.license.controller;

import com.llcgrowth.license.model.License;
import com.llcgrowth.license.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/organisation/{organizationId}/license")
public class LicenseController {
	private LicenseService licenseService;

	@GetMapping(value = "/{licenseId}")
	public ResponseEntity<License> getLicense(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId
	) {

		var license = licenseService.getLicense(licenseId, organizationId);
		license.add(linkTo(methodOn(LicenseController.class)
						.getLicense(organizationId, license.getLicenseId()))
						.withSelfRel(),
				linkTo(methodOn(LicenseController.class)
						.createLicense(organizationId, license, null))
						.withRel("createLicense"),
				linkTo(methodOn(LicenseController.class)
						.updateLicense(organizationId, license))
						.withRel("updateLicense"),
				linkTo(methodOn(LicenseController.class)
						.deleteLicense(organizationId, license.getLicenseId())).withRel("deleteLicense"));

		return ResponseEntity.ok(license);
	}

	@PutMapping
	public ResponseEntity<String> updateLicense(
			@PathVariable("organizationId") String organizationId,
			@RequestBody License license
	) {
		return ResponseEntity.ok(licenseService.updateLicense(license, organizationId));
	}

	@PostMapping
	public ResponseEntity<String> createLicense(
			@PathVariable("organizationId") String organisationId,
			@RequestBody License license,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale
	) {
		return ResponseEntity.ok(licenseService.createLicense(license, organisationId, locale));
	}

	@DeleteMapping(value = "/{licenseId}")
	public ResponseEntity<String> deleteLicense(
			@PathVariable("organizationId") String organisationId,
			@PathVariable("licenseId") String licenseId
	) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organisationId));
	}
}
