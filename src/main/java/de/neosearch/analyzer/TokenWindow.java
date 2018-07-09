package de.neosearch.analyzer;

import java.util.List;
import java.util.stream.Collectors;

public class TokenWindow extends Token {
	public TokenWindow(List<Token> tokens) {
		super();

		if (tokens != null && !tokens.isEmpty())
			initFields(tokens);

	}

	private void initFields(List<Token> tokens) {
		this.setBegin(tokens.get(0).getBegin());
		this.setEnd(tokens.get(tokens.size() - 1).getEnd());
		String str = tokens.stream().map(t -> t.getString()).collect(Collectors.joining(""));
		this.setString(str);
		this.setTokenType(null);
	}

}
