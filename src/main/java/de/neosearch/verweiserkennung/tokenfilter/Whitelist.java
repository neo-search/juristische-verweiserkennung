package de.neosearch.verweiserkennung.tokenfilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Whitelist {

	private Map<String, Map<String, String>> whitelistMap = new HashMap<>();

	public Whitelist add(String tokenType, String string, String normalizedString) {
		String k = string.toLowerCase();
		String v = normalizedString.toLowerCase();
		if (!whitelistMap.containsKey(tokenType))
			whitelistMap.put(tokenType, new HashMap<>());
		whitelistMap.get(tokenType).put(k, v);
		return this;
	}

	public Optional<WhitelistEntry> get(String tokenValue) {
		for (Entry<String, Map<String, String>> tokenMap : whitelistMap.entrySet()) {
			String result = tokenMap.getValue().get(tokenValue);
			if (result != null) {
				return Optional.of(new WhitelistEntry(tokenMap.getKey(), result));
			}
		}
		return Optional.empty();
	}

}
