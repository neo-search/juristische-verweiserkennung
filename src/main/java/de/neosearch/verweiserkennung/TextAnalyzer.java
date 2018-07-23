package de.neosearch.verweiserkennung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.neosearch.verweiserkennung.tokenfilter.WhitelistFilter;
import de.neosearch.verweiserkennung.tokenizer.Shingle;
import de.neosearch.verweiserkennung.tokenizer.ShingleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.SimpleTokenizer;
import de.neosearch.verweiserkennung.tokenizer.Token;

public class TextAnalyzer {

	// private Whitelist whiteList = new Whitelist();

	public static class TextAnalyzerBuilder {

		private List<WhitelistFilter> filters = new ArrayList<>();
		private int maxWindowSize;

		private TextAnalyzerBuilder(int maxWindowSize) {
			this.maxWindowSize = maxWindowSize;
		}

		public TextAnalyzerBuilder add(WhitelistFilter filter) {
			filters.add(filter);
			return this;
		}

		public TextAnalyzer build() {
			TextAnalyzer textAnalyzer = new TextAnalyzer(maxWindowSize, filters);
			return textAnalyzer;
		}

	}

	private int maxWindowSize;
	private List<WhitelistFilter> whitelistFilters;

	private TextAnalyzer(int maxWindowSize, List<WhitelistFilter> whitelistFilters) {
		this.maxWindowSize = maxWindowSize;
		this.whitelistFilters = whitelistFilters;
	}

	public static TextAnalyzerBuilder createTextAnalyzer(int maxWindowSize) {
		return new TextAnalyzerBuilder(maxWindowSize);
	}

	public AnalyzedText analyze(String query) {
		return analyze(query, maxWindowSize);
	}

	private AnalyzedText analyze(String text, int windowSize) {

		List<Token> tokens = new SimpleTokenizer().tokenize(text);
		ShingleTokenizer shingleTokenizer = new ShingleTokenizer(tokens, windowSize);
		List<Token> result = new ArrayList<>();
		while (shingleTokenizer.hasMoreShingles()) {
			Shingle moreShingle = shingleTokenizer.moreShingle();

			boolean foundToken = false;
			while (moreShingle.hasMoreTokens()) {
				Token token = moreShingle.moreToken();
				for (WhitelistFilter filter : whitelistFilters) {
					Token filteredToken;
					if ((filteredToken = filter.accept(token)) != null) {
						result.add(filteredToken);
						foundToken = true;
						shingleTokenizer.skipTo(filteredToken.getEnd());
						break;
					}
				}
				if (foundToken == true)
					break;
			}

			if (foundToken == false) {
				Token token = moreShingle.getUniGram();
				for (WhitelistFilter filter : whitelistFilters) {
					Token filteredToken;
					if ((filteredToken = filter.accept(token)) != null) {
						result.add(filteredToken);
						foundToken = true;
					}
				}
			}

			if (foundToken == false)
				result.add(moreShingle.getUniGram());

		}
		return new AnalyzedText(result);
	}

	// private Optional<WhitelistEntry> analyzeToken(Token token) {
	// String cleanedToken = removeAnchorInformation(token.getNormalizedString());
	//
	// Optional<WhitelistEntry> optionalWhitelistEntry =
	// whiteList.get(cleanedToken);
	//
	// if (optionalWhitelistEntry.isPresent())
	// return optionalWhitelistEntry;
	//
	// return Optional.empty();
	// }
	//
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

	public static void main(String[] args) throws IOException {
	}

}
