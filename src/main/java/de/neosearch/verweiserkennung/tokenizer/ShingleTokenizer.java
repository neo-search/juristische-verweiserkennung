package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;

public class ShingleTokenizer {
	private int index;
	private final List<Token> tokens;
	private final int maxShingleSize;

	public ShingleTokenizer(List<Token> tokens, int maxShingleSize) {
		this.tokens = tokens;
		this.maxShingleSize = maxShingleSize;
		this.index = 0;
	}

	public boolean hasMoreShingles() {
		return index < tokens.size();
	}

	public Shingle moreShingle() {
		if (index >= tokens.size())
			return null;

		Shingle shingle = new Shingle(tokens.subList(index, Math.min(index + maxShingleSize, tokens.size())));
		index++;
		return shingle;
	}

}
