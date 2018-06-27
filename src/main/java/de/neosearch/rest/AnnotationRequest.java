package de.neosearch.rest;

import lombok.Data;

@Data
public class AnnotationRequest {
	private String text;
	private String targetPlatform; // urteile-gesetze (default) oder opendata
}
