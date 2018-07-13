package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;
import java.util.stream.Collectors;

public class Shingle {
	private int index = 0;
	private Token uniGram;
	private List<Token> tokens;

	public Shingle(List<Token> tokens) {
		this.tokens = tokens;
		this.index = tokens.size();
		this.uniGram = tokens.isEmpty() ? null : tokens.get(0);
	}

	public boolean hasMoreToken() {
		return index > 1;
	}

	public Token moreToken() {
		if (index <= 1)
			return null;
		int begin = tokens.get(0).getBegin();
		int end = tokens.get(index - 1).getEnd();
		String string = tokens.subList(0, index).stream().map(t -> t.getString()).collect(Collectors.joining());
		String normalizedString = tokens.subList(0, index).stream().map(t -> t.getNormalizedString())
				.collect(Collectors.joining());

		index--;
		return new Token(begin, end, string, normalizedString);
	}

	public Token getUniGram() {
		return uniGram;
	}
}
