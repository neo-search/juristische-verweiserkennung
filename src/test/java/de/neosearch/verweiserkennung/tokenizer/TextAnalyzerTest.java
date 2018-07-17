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

	private LowercaseWhitelistFilter lowerFilter = new LowercaseWhitelistFilter("testToken", Arrays.asList("Test A"),
			Arrays.asList("/test"));;
	private LowercaseWhitelistFilter sectionFilter = new LowercaseWhitelistFilter("sectionToken",
			Arrays.asList("BGB §A"), Arrays.asList("/bgb/a"));
	private TextAnalyzer textAnalyzer = TextAnalyzer.createTextAnalyzer(3).add(lowerFilter).add(sectionFilter).build();

	@Test
	void testTextAnalyse() {
		List<Token> tokens = textAnalyzer.analyze("Test a a");
		assertEquals(new Token(0, 6, "Test a", "/test", "testToken"), tokens.get(0));
		assertEquals(new Token(6, 7, " ", " ", "whitespace"), tokens.get(1), "whitespace");
		assertEquals(new Token(7, 8, "a", "a", "text"), tokens.get(2), "character");
		System.out.println(tokens);
	}

}