package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ShingleTest {

	Token token1 = new Token(0, 6, "token1", TokenTypes.TEXT);
	Token token2 = new Token(6, 8, " ", TokenTypes.WHITESPACE);
	Token token3 = new Token(8, 9, "/", TokenTypes.SPECIALCHARS);
	Token token4 = new Token(9, 10, " ", TokenTypes.WHITESPACE);
	Token token5 = new Token(10, 14, "ende", TokenTypes.TEXT);

	Shingle shingle = new Shingle(Arrays.asList(token1, token2, token3, token4, token5));

	@Test
	public void testShingle() {
		assertTrue(shingle.hasMoreTokens());
		assertEquals(new Token(0, 14, "Token1  / ende", "token1 / ende"), shingle.moreToken());
		assertTrue(shingle.hasMoreTokens());
		assertEquals(new Token(0, 9, "Token1  /", "token1 /"), shingle.moreToken());
		assertFalse(shingle.hasMoreTokens());
		assertEquals(null, shingle.moreToken());
		assertEquals(new Token(0, 6, "Token1", "token1"), shingle.getUniGram());
	}

}