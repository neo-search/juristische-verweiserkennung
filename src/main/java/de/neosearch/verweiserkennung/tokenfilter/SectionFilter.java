package de.neosearch.verweiserkennung.tokenfilter;

import java.util.List;

import de.neosearch.verweiserkennung.tokenizer.Token;

public abstract class SectionFilter extends LowercaseWhitelistFilter {

	public SectionFilter(String tokenType, List<String> token, List<String> normalizedToken) {
		super(tokenType, token, normalizedToken);
//		List<String> lowercasetokens = token.stream().map(String::toLowerCase).collect(Collectors.toList());
//		List<String> lowercasenormalizedTokens = token.stream().map(String::toLowerCase).collect(Collectors.toList());
//
//		for (int i = 0; i < lowercasetokens.size(); i++)
//			addToWhitelist(lowercasetokens.get(i), lowercasenormalizedTokens.get(i));
	}

	public Token accept(Token token) {
		String tokenString = removeAnchorInformation(token.getNormalizedString());
		if (whitelistContains(tokenString))
			return token.withTokenType(this.tokenType);
		return null;
	};

	public static String removeAnchorInformation(String normAbkuerzung) {
		return normAbkuerzung //
				.replaceAll(" abs\\. \\d+", "")//
				.replaceAll(" satz \\d+", "")//
				.replaceAll(" s \\d+", "")//
				.replaceAll(" nr \\d+", "")//
				.replaceAll(" nr\\. \\d+", "")//
				.replaceAll(" alt \\d+", "")//
				.replaceAll(" buchst \\w", "")//
				.replaceAll(" vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d", "");
	}
}
