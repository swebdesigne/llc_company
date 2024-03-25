package com.llcgrowth.license.service;

import com.llcgrowth.license.model.License;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LicenseService {
	public License getLicense(String licenseId, String organisationId) {
		return License.builder()
				.id(new Random().nextInt(1000))
				.licenseId(licenseId)
				.organisationId(organisationId)
				.description("Software product")
				.productName("Ostock")
				.licenseType("full")
				.build();
	}

	public String createLicense(License license, String organizationId) {
		String responseMessage = null;
		if (license != null) {
			license.setOrganisationId(organizationId);
			responseMessage = String.format(
					"This is the post and the object is: %s", license.toString());
		}
		return responseMessage;
	}

	public String updateLicense(License license, String organizationId) {
		String responseMessage = null;
		if (license != null) {
			license.setOrganisationId(organizationId);
			responseMessage = String.format(
					"This is the put and the object is: %s", license.toString());
		}
		return responseMessage;
	}

	public String deleteLicense(String licenseId, String organizationId) {
		String responseMessage = null;
		responseMessage = String.format(
				"Deleting license with id %s for the organization %s",
				licenseId, organizationId);
		return responseMessage;
	}
}
