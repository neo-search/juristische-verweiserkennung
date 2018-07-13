package de.neosearch.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

import de.neosearch.verweiserkennung.tokenfilter.WhitelistFilter;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger log = LoggerFactory.getLogger(RestController.class);
	private List<WhitelistFilter> whitelistFilters = new ArrayList<>();

	@PostConstruct
	public void init() {
//		WhitelistFilter aktenzeichenFilter = new WhitelistFilter();
		

	}

	@PostMapping
	public void annotate(AnnotationRequest req) {
		if (req == null || req.getText() == null)
			throw new RuntimeException("Nothing to annotate");
		String text = req.getText();

		log.info("Annotating Request : " + req.getText().substring(0, Math.max(15, req.getText().length())) + "...");

	}
}
