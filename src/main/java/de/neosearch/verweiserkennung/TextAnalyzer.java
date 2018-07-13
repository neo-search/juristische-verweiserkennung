package de.neosearch.verweiserkennung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.neosearch.verweiserkennung.tokenfilter.Whitelist;
import de.neosearch.verweiserkennung.tokenfilter.WhitelistEntry;
import de.neosearch.verweiserkennung.tokenfilter.WhitelistFilter;
import de.neosearch.verweiserkennung.tokenizer.Shingle;
import de.neosearch.verweiserkennung.tokenizer.ShingleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.SimpleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.Token;

public class TextAnalyzer {

	private int MAX_WINDOW_SIZE = 20;
	private Whitelist whiteList = new Whitelist();

	private List<WhitelistFilter> whitelistFilters;

	public TextAnalyzer(List<WhitelistFilter> whitelistFilters) {
		this.whitelistFilters = whitelistFilters;
	}

	public AnalyzedText analyze(String query) {
		AnalyzedText analyze = analyze(query, MAX_WINDOW_SIZE);
		// Log.logger.debug(analyze + "");
		return analyze;
	}

	private AnalyzedText analyze(String text, int windowSize) {

		List<Token> tokens = new SimpleTokenizer().tokenize(text);
		ShingleTokenizer shingleTokenizer = new ShingleTokenizer(tokens, windowSize);
		List<Token> result = new ArrayList<>();
		while (shingleTokenizer.hasMoreShingles()) {
			Shingle moreShingle = shingleTokenizer.moreShingle();

			boolean foundToken = false;
			while (moreShingle.hasMoreToken()) {
				Token token = moreShingle.moreToken();
				Optional<WhitelistEntry> whitelistEntryOption = analyzeToken(token);
				if (whitelistEntryOption.isPresent()) {
					WhitelistEntry whitelistEntry = whitelistEntryOption.get();
					result.add(new Token(token.getBegin(), token.getEnd(), token.getString(), whitelistEntry.getValue(),
							whitelistEntry.getTokenType()));
					foundToken = true;
					break;
				}
			}
			if (foundToken == false) {
				Token token = moreShingle.getUniGram();
				Optional<WhitelistEntry> whitelistEntryOption = analyzeToken(token);
				if (whitelistEntryOption.isPresent()) {
					WhitelistEntry whitelistEntry = whitelistEntryOption.get();
					result.add(new Token(token.getBegin(), token.getEnd(), token.getString(), whitelistEntry.getValue(),
							whitelistEntry.getTokenType()));
				} else {
					result.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
							token.getNormalizedString(), token.getTokenType()));
				}
			}

		}
		return analyze(text, windowSize);
	}

	private Optional<WhitelistEntry> analyzeToken(Token token) {
		String cleanedToken = removeAnchorInformation(token.getNormalizedString());

		Optional<WhitelistEntry> optionalWhitelistEntry = whiteList.get(cleanedToken);

		if (optionalWhitelistEntry.isPresent())
			return optionalWhitelistEntry;

		return Optional.empty();
	}

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

	public static void main(String[] args) throws IOException {
//		TextAnalyzer an = new TextAnalyzer();
		// an.addNormToWhitelist("BGB", "/gesetze/bgb");
//		an.addSectionToWhitelist("BGB", "§ 3", "", "/gesetze/bgb/3");
		// an.readInWhitelists();
		// an.analyzeToken(token)
		// Text analyze = an.analyze("Hello BGB §3 ii und so BGB ZPO id neu BGB
		// §15 ZPO.");
//		AnalyzedText analyze = an.analyze("BGB Mensch");

//		System.out.println(analyze);
	}

}
