package de.neosearch.verweiserkennung.tokenfilter;

import java.util.HashMap;

import de.neosearch.verweiserkennung.tokenizer.Token;

public abstract class WhitelistFilter {
	private HashMap<String, String> tokens = new HashMap<>();

	protected String tokenType;

	public abstract Token accept(Token token);

	public WhitelistFilter(String tokenType) {
		this.tokenType = tokenType;
	}

	protected void addToWhitelist(String key, String value) {
		this.tokens.put(key, value);
	}

	protected boolean whitelistContains(String token) {
		return tokens.containsKey(token);
	}

	protected String getNormalized(String token) {
		return tokens.get(token);
	}

}
