package de.neosearch.verweiserkennung.tokenfilter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import de.neosearch.verweiserkennung.tokenizer.Token;

public class LowercaseWhitelistFilter extends WhitelistFilter {

	private String tokenType;

	public LowercaseWhitelistFilter(String tokenType, List<String> token, List<String> normalizedToken) {
		this.tokenType = tokenType;
		List<String> lowercasetokens = token.stream().map(String::toLowerCase).collect(Collectors.toList());
		List<String> lowercasenormalizedTokens = normalizedToken.stream().map(String::toLowerCase)
				.collect(Collectors.toList());

		for (int i = 0; i < lowercasetokens.size(); i++)
			addToWhitelist(lowercasetokens.get(i), lowercasenormalizedTokens.get(i));

	}

	public LowercaseWhitelistFilter(String tokenType, Map<String, String> tokenNormalizedToken) {
		this.tokenType = tokenType;
		for (Entry<String, String> e : tokenNormalizedToken.entrySet())
			addToWhitelist(e.getKey().toLowerCase(), e.getValue().toLowerCase());

	}

	public Token accept(Token token) {
		if (whitelistContains(token.getNormalizedString()))
			return new Token(token.getBegin(), token.getEnd(), token.getString(),
					getNormalized(token.getNormalizedString()), tokenType);
		return null;
	};

}
