package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;
import java.util.stream.Collectors;

public class Shingle {
	private int index = 0;
	private Token uniGram;
	private List<Token> tokens;
	private String tokenType;
//	private int begin;

	public Shingle(List<Token> tokens) {
		this(tokens, null);
	}

	public Shingle(List<Token> tokens, String tokenType) {
		this.tokens = tokens;
		this.index = tokens.size();
		this.uniGram = tokens.isEmpty() ? null : tokens.get(0);
		this.tokenType = tokenType;
	}

	public boolean hasMoreTokens() {
		if (currentTokenIsWhitespace() && index <= 2)
			return false;
		return index > 1;
	}

	public Token moreToken() {
		boolean currentTokenIsWhitespace = currentTokenIsWhitespace();
		if (currentTokenIsWhitespace && index <= 2)
			return null;
		if (index <= 1)
			return null;

		int begin = tokens.get(0).getBegin();
		int amountOfTokensToStep = currentTokenIsWhitespace ? 2 : 1;
		int end = tokens.get(index - amountOfTokensToStep).getEnd();
		List<Token> subList = tokens.subList(0, index - amountOfTokensToStep + 1);

		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder normalizeStringBuilder = new StringBuilder();
		for (Token token : subList) {
			stringBuilder.append(token.getString());
			normalizeStringBuilder.append(token.getNormalizedString());
		}

		index = index - amountOfTokensToStep;
		return new Token(begin, end, stringBuilder.toString(), normalizeStringBuilder.toString());
	}

	private boolean currentTokenIsWhitespace() {
		Token currentToken = currentToken();
		return currentToken.isWhitespace();
	}

	public Shingle withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	private Token currentToken() {
		return tokens.get(index - 1);
	}

	public Token getUniGram() {
		return uniGram;
	}

	public String getTokenType() {
		return tokenType;
	}
}
