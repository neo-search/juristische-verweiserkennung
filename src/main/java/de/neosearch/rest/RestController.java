package de.neosearch.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger log = LoggerFactory.getLogger(RestController.class);

	@PostMapping
	public void annotate(AnnotationRequest req) {
		if (req == null || req.getText() == null)
			throw new RuntimeException("Nothing to annotate");
		log.info("Annotating Request : " + req.getText().substring(0, Math.max(15, req.getText().length())) + "...");

		
	}
}
