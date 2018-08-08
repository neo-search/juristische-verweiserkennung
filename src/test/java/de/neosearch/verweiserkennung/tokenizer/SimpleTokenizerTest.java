package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SimpleTokenizerTest {

	@Test
	public void testTokenizer() {
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		List<Token> tokens = tokenizer.tokenize("Test,  test 2");
		assertEquals(new Token(0, 4, "test", TokenTypes.TEXT), tokens.get(0));
		assertEquals(new Token(4, 5, ",", TokenTypes.SPECIALCHARS), tokens.get(1));
		assertEquals(new Token(5, 7, " ", TokenTypes.WHITESPACE), tokens.get(2));
		assertEquals(new Token(7, 11, "test", TokenTypes.TEXT), tokens.get(3));
		assertEquals(new Token(11, 12, " ", TokenTypes.WHITESPACE), tokens.get(4));
		assertEquals(new Token(12, 13, "2", TokenTypes.TEXT), tokens.get(5));
	}

	@Test
	public void testShingleTokenizer() {
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		List<Token> tokens = tokenizer.tokenize("Test ta,");
		ShingleTokenizer shingleTokenizer = new ShingleTokenizer(tokens, 2);

		assertTrue(shingleTokenizer.hasMoreShingles());
		Shingle shingle = shingleTokenizer.moreShingle();
		assertTrue(shingle.hasMoreTokens());
		assertEquals(new Token(0, 7, "test ta"), shingle.moreToken());
		assertFalse(shingle.hasMoreTokens());
		assertEquals(shingle.moreToken(), null);
		assertEquals(new Token(0, 4, "test", TokenTypes.TEXT), shingle.getUniGram());

		assertTrue(shingleTokenizer.hasMoreShingles());
		shingle = shingleTokenizer.moreShingle();
		assertTrue(shingle.hasMoreTokens());
		assertEquals(new Token(5, 8, " ta,"), shingle.moreToken());
		assertFalse(shingle.hasMoreTokens());
		assertEquals(shingle.moreToken(), null);
		assertEquals(new Token(5, 7, "ta", TokenTypes.TEXT), shingle.getUniGram());

		assertTrue(shingleTokenizer.hasMoreShingles());
		shingle = shingleTokenizer.moreShingle();
//		assertTrue(shingle.hasMoreTokens());
//		assertEquals(shingle.moreToken(), new Token(5, 8, ",", ","));
		assertFalse(shingle.hasMoreTokens());
		assertEquals(shingle.moreToken(), null);
		assertEquals(new Token(7, 8, ",", TokenTypes.TEXT), shingle.getUniGram());

//		assertTrue(shingleTokenizer.hasMoreShingles());
//		shingle = shingleTokenizer.moreShingle();
//		assertFalse(shingle.hasMoreTokens());
//		assertEquals(shingle.getUniGram(), new Token(7, 8, ",", ","));

		assertFalse(shingleTokenizer.hasMoreShingles());
		assertNull(shingleTokenizer.moreShingle());

	}

}