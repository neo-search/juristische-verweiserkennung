package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.neosearch.verweiserkennung.AnalyzedText;
import de.neosearch.verweiserkennung.TextAnalyzer;
import de.neosearch.verweiserkennung.tokenfilter.LowercaseWhitelistFilter;
import de.neosearch.verweiserkennung.tokenizer.SimpleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.Token;

class TextAnalyzerTest {

	@Test
	void testTextAnaly() {
		LowercaseWhitelistFilter lowerFilter = new LowercaseWhitelistFilter("testToken", Arrays.asList("Test A"),
				Arrays.asList("/test"));
		LowercaseWhitelistFilter sectionFilter = new LowercaseWhitelistFilter("sectionToken", Arrays.asList("BGB §A"),
				Arrays.asList("/bgb/a"));
		TextAnalyzer textAnalyzer = TextAnalyzer.createTextAnalyzer(3).add(lowerFilter).add(sectionFilter).build();

		AnalyzedText analyzedText = textAnalyzer.analyze("Test A a");
		System.out.println(analyzedText);
	}

}