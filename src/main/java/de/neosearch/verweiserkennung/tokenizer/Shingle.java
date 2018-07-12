package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;
import java.util.stream.Collectors;

public class Shingle {
	private int index = 0;
	private List<Token> tokens;

	public Shingle(List<Token> tokens) {
		this.tokens = tokens;
		this.index = tokens.size() - 1;
	}

	public boolean hasMoreToken() {
		return index > 0;
	}

	public Token moreToken() {
		int begin = tokens.get(0).getBegin();
		int end = tokens.get(index).getEnd();
		String string = tokens.stream().map(t -> t.getString()).collect(Collectors.joining());
		String normalizedString = tokens.stream().map(t -> t.getNormalizedString()).collect(Collectors.joining());

		index--;
		return new Token(begin, end, string, normalizedString);

	}
}
