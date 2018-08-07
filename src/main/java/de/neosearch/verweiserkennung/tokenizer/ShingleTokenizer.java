package de.neosearch.verweiserkennung.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class ShingleTokenizer {
	private int index;
	private final List<Token> tokens;
	private final int amountOfTokensToGet;

	public ShingleTokenizer(List<Token> tokens, int maxShingleSize) {
		this.tokens = tokens;
		this.index = 0;
		this.amountOfTokensToGet = Math.min(maxShingleSize, tokens.size());
	}

	public boolean hasMoreShingles() {
		if (index >= tokens.size())
			return false;
		if (currentTokenIsWhitespace())
			return index + 1 < tokens.size();
		return index < tokens.size();
	}

	public Shingle moreShingle() {
		if (!hasMoreShingles())
			return null;

		Shingle shingle = nextShingle();
		return shingle;
	}

	private boolean currentTokenIsWhitespace() {
		return tokenIsWhitespace(index);
	}

	private boolean tokenIsWhitespace(int i) {
		return tokens.get(i).isWhitespace();
	}

	private Shingle nextShingle() {
		List<Token> shingleTokens = new ArrayList<>();
		int amountOfNonWhitespaceTokens = 0;

		if (currentTokenIsWhitespace())
			index++;

		int i = index;
		while (i < tokens.size()) {
			if (amountOfNonWhitespaceTokens == amountOfTokensToGet)
				break;
			if (!tokenIsWhitespace(i))
				amountOfNonWhitespaceTokens++;
			shingleTokens.add(tokens.get(i));
			i++;
		}
		index++;
		Shingle shingle = new Shingle(shingleTokens, shingleTokens.get(0).getTokenType());
		return shingle;
	}

	public void skipTo(int end) {
		while (true) {
			Shingle nextShingle = nextShingle();
			if (!nextShingle.hasMoreTokens()) {
				if (nextShingle.getUniGram().getBegin() < end)
					index++;
				break;
			}
			if (nextShingle.moreToken().getBegin() >= end)
				break;
			index++;
		}
	}

	public void skipTo2(int end) {
		while (hasMoreShingles()) {
			Shingle shingle = nextShingle();
			if (nextShingle().moreToken().getBegin() >= end)
				break;
			if (!shingle.hasMoreTokens() && shingle.getUniGram().getBegin() >= end)
				break;
		}
	}
}
