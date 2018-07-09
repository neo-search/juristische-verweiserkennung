package de.neosearch.analyzer;

import java.util.LinkedList;
import java.util.List;

public class SimpleTokenizer {
	static final String SPECIALCHARS_TOKENTYPE = "specialchars";
	static final String TEXT_TOKENTYPE = "text";
	static final String WHITESPACE_TOKENTYPE = "whitespace";

	@Override
	public String toString() {
		return "AnalyzedText=" + tokens;
	}

	private LinkedList<Token> tokens = new LinkedList<>();

//	public SimpleTokenizer(List<Token> tokenList) {
//		Collections.sort(tokenList);
//		this.tokens = new LinkedList<>(tokenList);
//	}

	public void remove(int begin, int end) {
		tokens.removeIf(t -> t.getBegin() >= begin && t.getEnd() <= end);
	}

	public LinkedList<Token> getTokens() {
		return tokens;
	}

	public int getSize() {
		return tokens.size();
	}

	public TokenWindow tokenWindow(int begin, int size) {
		return new TokenWindow(tokens.subList(begin, begin + size));
	}

	public LinkedList<Token> tokenize(String text) {
		if (text == null)
			return new LinkedList<>();

		LinkedList<Token> result = new LinkedList<>();
		String actualString = "";
		String typeOfLastChar = null;

		for (int i = 0, n = text.length(); i < n; i++) {
			char c = text.charAt(i);
			if (typeOfLastChar == null) {
				actualString = Character.toString(c);
				typeOfLastChar = Character.isWhitespace(c) ? WHITESPACE_TOKENTYPE
						: (Character.isLetterOrDigit(c) || c == '§' || c == '/') ? TEXT_TOKENTYPE
								: SPECIALCHARS_TOKENTYPE;

			} else if (Character.isWhitespace(c)) {
				if (!typeOfLastChar.equals(WHITESPACE_TOKENTYPE)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = WHITESPACE_TOKENTYPE;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = WHITESPACE_TOKENTYPE;
					actualString += c;
				}
			} else if (Character.isLetterOrDigit(c) || c == '§' || c == '/') {
				if (!typeOfLastChar.equals(TEXT_TOKENTYPE)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = TEXT_TOKENTYPE;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = TEXT_TOKENTYPE;
					actualString += c;
				}
			} else {
				if (!typeOfLastChar.equals(SPECIALCHARS_TOKENTYPE)) {
					int beginOfWord = i - actualString.length();
					result.add(new Token(beginOfWord, i, actualString, actualString.toLowerCase(), typeOfLastChar));
					typeOfLastChar = SPECIALCHARS_TOKENTYPE;
					actualString = Character.toString(c);
				} else {
					typeOfLastChar = SPECIALCHARS_TOKENTYPE;
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

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		// 170ms im Durchschnitt
		List<Token> tokens = new SimpleTokenizer()
				.tokenize("1. Art. 2 Buchst. d der Richtlinie 95/46/EG des Europäischen Parlaments.");

//		System.out.println(analyzedText);
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

}
