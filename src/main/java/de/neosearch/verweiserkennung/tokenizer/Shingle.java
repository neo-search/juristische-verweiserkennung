package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;

public class Shingle {
	private int index = 0;
	private Token uniGram;
	private List<Token> tokens;

	public Shingle(List<Token> tokens) {
		this.tokens = tokens;
		this.index = tokens.size();
		this.uniGram = tokens.isEmpty() ? null : tokens.get(0);
	}

	public boolean hasMoreTokens() {
		return index > 1;
	}

	public Token moreToken() {
		if (index <= 1)
			return null;
		int begin = tokens.get(0).getBegin();
		int end = tokens.get(index - 1).getEnd();
		List<Token> subList = tokens.subList(0, index);

		String string = "";
		String normalizedString = "";
		for (Token token : subList) {
			string += token.getString();
			normalizedString += token.getNormalizedString();
		}

		index--;
		return new Token(begin, end, string, normalizedString);
	}

	public Token getUniGram() {
		return uniGram;
	}
}
