package de.neosearch.verweiserkennung.tokenfilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import de.neosearch.verweiserkennung.tokenizer.Token;

import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LowercaseWhitelistFilter extends WhitelistFilter {

	private HashMap<String, String> tokens = new HashMap<>();
	private String tokenType;

	public LowercaseWhitelistFilter(String tokenType, List<String> token, List<String> normalizedToken) {
		this.tokenType = tokenType;
		List<String> lowercasetokens = token.stream().map(String::toLowerCase).collect(Collectors.toList());
		List<String> lowercasenormalizedTokens = normalizedToken.stream().map(String::toLowerCase)
				.collect(Collectors.toList());

		for (int i = 0; i < lowercasetokens.size(); i++)
			this.tokens.put(lowercasetokens.get(i), lowercasenormalizedTokens.get(i));

	}

	public Token accept(Token token) {
		if (this.tokens.containsKey(token.getNormalizedString()))
			return new Token(token.getBegin(), token.getEnd(), token.getString(),
					this.tokens.get(token.getNormalizedString()), tokenType);
		return null;
	};

	// public static String removeAnchorInformation(String normAbkuerzung) {
	// return normAbkuerzung //
	// .replaceAll(" abs\\. \\d+", "")//
	// .replaceAll(" satz \\d+", "")//
	// .replaceAll(" s \\d+", "")//
	// .replaceAll(" nr \\d+", "")//
	// .replaceAll(" nr\\. \\d+", "")//
	// .replaceAll(" alt \\d+", "")//
	// .replaceAll(" buchst \\w", "")//
	// .replaceAll(" vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d", "");
	// }

	// boolean foundToken = false;
	// while (moreShingle.hasMoreToken()) {
	// Token token = moreShingle.moreToken();
	// Optional<WhitelistEntry> whitelistEntryOption = analyzeToken(token);
	// if (whitelistEntryOption.isPresent()) {
	// WhitelistEntry whitelistEntry = whitelistEntryOption.get();
	// result.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
	// whitelistEntry.getValue(),
	// whitelistEntry.getTokenType()));
	// foundToken = true;
	// break;
	// }
	// }
	// if (foundToken == false) {
	// Token token = moreShingle.getUniGram();
	// Optional<WhitelistEntry> whitelistEntryOption = analyzeToken(token);
	// if (whitelistEntryOption.isPresent()) {
	// WhitelistEntry whitelistEntry = whitelistEntryOption.get();
	// result.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
	// whitelistEntry.getValue(),
	// whitelistEntry.getTokenType()));
	// } else {
	// result.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
	// token.getNormalizedString(), token.getTokenType()));
	// }
	// }
	//
	// }
	//

	// public Optional<WhitelistEntry> get(String tokenValue) {
	// for (Entry<String, Map<String, String>> tokenMap : whitelistMap.entrySet()) {
	// String result = tokenMap.getValue().get(tokenValue);
	// if (result != null) {
	// return Optional.of(new WhitelistEntry(tokenMap.getKey(), result));
	// }
	// }
	// return Optional.empty();
	// }
}
