package de.neosearch.verweiserkennung.tokenfilter;

import java.util.HashMap;

import de.neosearch.verweiserkennung.tokenizer.Token;

public abstract class WhitelistFilter {
	private HashMap<String, String> tokens = new HashMap<>();

	private int minTokenSize = 1000;

	protected String tokenType;

	public Token accept(Token token) {
//		if (tooSmall(token))
//			return null;
		return acceptImpl(token);
	}

	public abstract Token acceptImpl(Token token);

	public WhitelistFilter(String tokenType) {
		this.tokenType = tokenType;
	}

	protected void addToWhitelist(String key, String value) {
		this.tokens.put(key, value);
		minTokenSize = Math.min(minTokenSize, key.length());
	}

	protected boolean tooSmall(Token token) {
		return token.getNormalizedString().length() < minTokenSize;
	}

	protected boolean whitelistContains(String token) {
		return tokens.containsKey(token);
	}

	protected String getNormalized(String token) {
		return tokens.get(token);
	}

}