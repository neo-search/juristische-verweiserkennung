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
	private LowercaseWhitelistFilter aktenzeichenFilter = new LowercaseWhitelistFilter("aktenzeichen",
			Arrays.asList("VII B 28/11"), Arrays.asList("vii-b-28/11"));
	private TextAnalyzer textAnalyzer = TextAnalyzer.createTextAnalyzer(8).add(lowerFilter).add(sectionFilter)
			.add(aktenzeichenFilter).build();

	@Test
	public void testTextAnalyse() {
		List<Token> tokens = textAnalyzer.analyze("Test a a").getTokens();
		assertEquals(tokens.size(), 3);
		assertEquals(new Token(0, 6, "Test a", "/test", "testToken"), tokens.get(0));
		assertEquals(new Token(6, 7, " ", " ", "whitespace"), tokens.get(1));
		assertEquals(new Token(7, 8, "a", "a", "text"), tokens.get(2));
	}

	@Test
	public void testOnlyOneTokens() {
		List<Token> tokens = textAnalyzer.analyze("VII B 28/11").getTokens();
		System.out.println(tokens);
		assertEquals(1, tokens.size());
		assertEquals(new Token(0, 11, "VII B 28/11", "vii-b-28/11", "aktenzeichen"), tokens.get(0));
	}

	@Test
	public void testOnlyTwoTokens() {
		List<Token> tokens = textAnalyzer.analyze("VII B 28/11 ").getTokens();
		System.out.println(tokens);
		assertEquals(2, tokens.size());
		assertEquals(new Token(0, 11, "VII B 28/11", "vii-b-28/11", "aktenzeichen"), tokens.get(0));
		assertEquals(new Token(11, 12, " ", " ", "whitespace"), tokens.get(1));
	}

}