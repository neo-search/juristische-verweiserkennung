package de.neosearch.verweiserkennung.tokenizer;

import static de.neosearch.verweiserkennung.tokenizer.TokenTypes.SPECIALCHARS;
import static de.neosearch.verweiserkennung.tokenizer.TokenTypes.TEXT;
import static de.neosearch.verweiserkennung.tokenizer.TokenTypes.WHITESPACE;

import java.util.ArrayList;
import java.util.List;

public class SimpleTokenizer {

	public List<Token> tokenize(String text) {
		if (text == null)
			return new ArrayList<>();

		List<Token> result = new ArrayList<>();
		StringBuilder actualString = new StringBuilder();
		StringBuilder normalizedString = new StringBuilder();
		String typeOfLastChar = null;

		for (int i = 0, n = text.length(); i < n; i++) {
			char c = text.charAt(i);
			if (typeOfLastChar == null) {
				actualString.append(c);
				normalizedString.append(normalizeChar(c));
				typeOfLastChar = isWhitespace(c) ? WHITESPACE : isTokenChar(c) ? TEXT : SPECIALCHARS;

			} else if (isWhitespace(c)) {
				if (!typeOfLastChar.equals(WHITESPACE)) {
					int beginOfWord = i - actualString.length();
					result.add(newToken(beginOfWord, i, actualString, normalizedString, typeOfLastChar));
					typeOfLastChar = WHITESPACE;
					actualString = new StringBuilder(Character.toString(c));
					normalizedString = new StringBuilder(Character.toString(normalizeChar(c)));
				} else {
					actualString.append(c);
					normalizedString.append(c);
				}
			} else if (isTokenChar(c)) {
				if (!typeOfLastChar.equals(TEXT)) {
					int beginOfWord = i - actualString.length();
					result.add(newToken(beginOfWord, i, actualString, normalizedString, typeOfLastChar));
					typeOfLastChar = TEXT;
					actualString = new StringBuilder(Character.toString(c));
					normalizedString = new StringBuilder(Character.toString(normalizeChar(c)));
				} else {
					actualString.append(c);
					normalizedString.append(normalizeChar(c));
				}
			} else {
				if (!typeOfLastChar.equals(SPECIALCHARS)) {
					int beginOfWord = i - actualString.length();
					result.add(newToken(beginOfWord, i, actualString, normalizedString, typeOfLastChar));
					typeOfLastChar = SPECIALCHARS;
					actualString = new StringBuilder(Character.toString(c));
					normalizedString = new StringBuilder(Character.toString(normalizeChar(c)));
				} else {
//					typeOfLastChar = SPECIALCHARS;
					actualString.append(c);
					normalizedString.append(normalizeChar(c));
				}
			}
		}

		if (actualString.length() > 0) {
			int beginOfWord = text.length() - actualString.length();
			result.add(newToken(beginOfWord, text.length(), actualString, normalizedString, typeOfLastChar));
		}

		return result;
	}

	private Token newToken(int beginOfWord, int i, StringBuilder actualString, StringBuilder normalizedString,
			String typeOfLastChar) {
		if (TokenTypes.WHITESPACE.equals(typeOfLastChar))
			return new Token(beginOfWord, i, " ", typeOfLastChar);
		return new Token(beginOfWord, i, normalizedString.toString(), typeOfLastChar);
	}

	private char normalizeChar(char ch) {
		return Character.toLowerCase(ch);
	}

	private boolean isWhitespace(char c) {
		return Character.isWhitespace(c) || '\u00A0' == c;
	}

	private boolean isTokenChar(char c) {
		return Character.isLetterOrDigit(c) || c == '§' || c == '/';
	}

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		// 170ms im Durchschnitt
		List<Token> tokens = new SimpleTokenizer()
				.tokenize("1. Art. 2 Buchst. d der Richtlinie 95/46/EG des Europäischen Parlaments.");

		// System.out.println(analyzedText);
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

}
