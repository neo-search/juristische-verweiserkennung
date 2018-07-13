package de.neosearch.verweiserkennung.tokenfilter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.neosearch.verweiserkennung.tokenizer.Token;

import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WhitelistFilter {

	
	WhitelistFilter(List<String> token, List<String> normalizedToken) {
		List<String> tokensLowercase = token.stream().map(String::toLowerCase).collect(Collectors.toList());
//		List<String> tokensLowercase = token.stream().map(String::toLowerCase).collect(Collectors.toList());
	}

	public void accept(Token token) {

//		if(token.getNormalizedString())

	}

//	public Optional<WhitelistEntry> get(String tokenValue) {
//		for (Entry<String, Map<String, String>> tokenMap : whitelistMap.entrySet()) {
//			String result = tokenMap.getValue().get(tokenValue);
//			if (result != null) {
//				return Optional.of(new WhitelistEntry(tokenMap.getKey(), result));
//			}
//		}
//		return Optional.empty();
//	}
}
