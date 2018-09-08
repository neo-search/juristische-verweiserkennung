package de.neosearch.verweiserkennung;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.neosearch.verweiserkennung.tokenizer.Token;

public class AnalyzedText {
	static final String SPECIALCHARS_TOKENTYPE = "specialchars";
	static final String TEXT_TOKENTYPE = "text";
	static final String WHITESPACE_TOKENTYPE = "whitespace";

	@Override
	public String toString() {
		return "AnalyzedText=" + tokens;
	}

	private List<Token> tokens;

	public AnalyzedText(List<Token> tokenList) {
		this.tokens = tokenList;
	}

	public List<Token> getTokens() {
		return tokens;
	}

//	public boolean hasOnlyType(TokenType tokenType) {
//		return tokens.size() == 1 && tokens.get(0).getTokenType() == tokenType;
//	}
//
//	public boolean hasAtLeastOnceType(TokenType tokenType) {
//		return tokens.stream().anyMatch(t -> t.getTokenType() == tokenType);
//		// return tokens.size() == 1 && tokens.get(0).getTokenType() ==
//		// tokenType;
//	}

	public int getSize() {
		return tokens.size();
	}

	public List<Token> getTokens(String tokenType) {
		return tokens.stream().filter(t -> t.getTokenType().equals(tokenType)).collect(Collectors.toList());
	}

	public Optional<Token> getFirstToken(String tokenType) {
		return getTokens(tokenType).stream().findFirst();
	}

	public static void main(String[] args) {

		// 170ms im Durchschnitt
		long currentTimeMillis = System.currentTimeMillis();
//		AnalyzedText analyzedText = new AnalyzedText(
//				"1. Art. 2 Buchst. d der Richtlinie 95/46/EG des Europäischen Parlaments.");

//		System.out.println(analyzedText);
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

}
