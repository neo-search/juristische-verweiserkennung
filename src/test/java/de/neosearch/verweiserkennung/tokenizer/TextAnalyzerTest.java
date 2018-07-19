package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.neosearch.verweiserkennung.TextAnalyzer;
import de.neosearch.verweiserkennung.tokenfilter.LowercaseWhitelistFilter;

public class TextAnalyzerTest {

	public TextAnalyzerTest() {
	}

	private LowercaseWhitelistFilter lowerFilter = new LowercaseWhitelistFilter("testToken", Arrays.asList("Test A"),
			Arrays.asList("/test"));;
	private LowercaseWhitelistFilter sectionFilter = new LowercaseWhitelistFilter("sectionToken",
			Arrays.asList("BGB §A"), Arrays.asList("/bgb/a"));
	private TextAnalyzer textAnalyzer = TextAnalyzer.createTextAnalyzer(3).add(lowerFilter).add(sectionFilter).build();

	@Test
	public void testTextAnalyse() {
		List<Token> tokens = textAnalyzer.analyze("Test a a").getTokens();
		assertEquals(new Token(0, 6, "Test a", "/test", "testToken"), tokens.get(0));
		assertEquals(new Token(6, 7, " ", " ", "whitespace"), tokens.get(1));
		assertEquals(new Token(7, 8, "a", "a", "text"), tokens.get(2));
		System.out.println(tokens);
	}

}