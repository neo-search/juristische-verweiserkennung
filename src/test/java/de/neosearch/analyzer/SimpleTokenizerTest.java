package de.neosearch.analyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class SimpleTokenizerTest {

	@Test
	void test() {
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		LinkedList<Token> tokens = tokenizer.tokenize("Test,  test 2");
		assertEquals(new Token(0, 4, "Test", "test", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(0));
		assertEquals(new Token(4, 5, ",", ",", SimpleTokenizer.SPECIALCHARS_TOKENTYPE), tokens.get(1));
		assertEquals(new Token(5, 7, "  ", "  ", SimpleTokenizer.WHITESPACE_TOKENTYPE), tokens.get(2));
		assertEquals(new Token(7, 11, "test", "test", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(3));
		assertEquals(new Token(11, 12, " ", " ", SimpleTokenizer.WHITESPACE_TOKENTYPE), tokens.get(4));
		assertEquals(new Token(12, 13, "2", "2", SimpleTokenizer.TEXT_TOKENTYPE), tokens.get(5));
	}

}
