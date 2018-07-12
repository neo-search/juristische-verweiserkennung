package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.neosearch.verweiserkennung.tokenizer.SimpleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.Token;

class SimpleTokenizerTest {

	@Test
	void testTokenizer() {
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		List<Token> tokens = tokenizer.tokenize("Test,  test 2");
		assertEquals(new Token(0, 4, "Test", "test", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(0));
		assertEquals(new Token(4, 5, ",", ",", SimpleTokenizer.SPECIALCHARS_TOKENTYPE), tokens.get(1));
		assertEquals(new Token(5, 7, "  ", "  ", SimpleTokenizer.WHITESPACE_TOKENTYPE), tokens.get(2));
		assertEquals(new Token(7, 11, "test", "test", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(3));
		assertEquals(new Token(11, 12, " ", " ", SimpleTokenizer.WHITESPACE_TOKENTYPE), tokens.get(4));
		assertEquals(new Token(12, 13, "2", "2", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(5));
	}

	@Test
	void testShingle() {
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		List<Token> tokens = tokenizer.tokenize("Test,  test 2");
		ShingleTokenizer shingleTokenizer = new ShingleTokenizer(tokens, 4);

		assertTrue(shingleTokenizer.hasMoreShingles());
		Shingle shingle = shingleTokenizer.moreShingle();
		assertTrue(shingle.hasMoreToken());
		assertEquals(shingle.moreToken(), new Token(0, 11, "Test,  test ", "test,  test "));
	}

}
