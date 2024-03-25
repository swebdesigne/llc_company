package com.llcgrowth.license.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Builder
public class License extends RepresentationModel<License> {
	private int id;
	private String licenseId;
	private String description;
	private String organisationId;
	private String productName;
	private String licenseType;
}
