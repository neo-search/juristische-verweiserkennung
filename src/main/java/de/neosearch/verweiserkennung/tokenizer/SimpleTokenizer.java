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
		String actualString = "";
		String typeOfLastChar = null;

		for (int i = 0, n = text.length(); i < n; i++) {
			char c = text.charAt(i);
			if (typeOfLastChar == null) {
				actualString = Character.toString(c);
				typeOfLastChar = isWhitespace(c) ? WHITESPACE
						: isTokenChar(c) ? TEXT : SPECIALCHARS;

			} else if (isWhitespace(c)) {
				if (!typeOfLastChar.equals(WHITESPACE)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = WHITESPACE;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = WHITESPACE;
					actualString += c;
				}
			} else if (Character.isLetterOrDigit(c) || c == '§' || c == '/') {
				if (!typeOfLastChar.equals(TEXT)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = TEXT;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = TEXT;
					actualString += c;
				}
			} else {
				if (!typeOfLastChar.equals(SPECIALCHARS)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = SPECIALCHARS;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = SPECIALCHARS;
					actualString += c;
				}
			}
		}

		if (!actualString.isEmpty()) {
			int beginOfWord = text.length() - actualString.length();
			result.add(new Token(beginOfWord, text.length(), actualString, actualString.toLowerCase(), typeOfLastChar));
		}

		return result;
	}

	private boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}

	private boolean isTokenChar(char c) {
		return Character.isLetterOrDigit(c) || c == '§' || c == '/';
	}

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		// 170ms im Durchschnitt
		List<Token> tokens = new SimpleTokenizer()
				.tokenize("1. Art. 2 Buchst. d der Richtlinie 95/46/EG des Europäischen Parlaments.");

//		System.out.println(analyzedText);
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

}
