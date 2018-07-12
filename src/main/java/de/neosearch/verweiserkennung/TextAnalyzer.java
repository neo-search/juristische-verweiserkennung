package de.neosearch.verweiserkennung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import de.neosearch.verweiserkennung.tokenizer.Token;

public class TextAnalyzer {

	private int MAX_WINDOW_SIZE = 20;
	private Whitelist whiteList = new Whitelist();

	public AnalyzedText analyze(String query) {
		AnalyzedText analyze = analyze(query, MAX_WINDOW_SIZE);
		// Log.logger.debug(analyze + "");
		return analyze;
	}

//	public String analyzeAndGenerateLinks(String string) {
//		if (string == null)
//			return null;
//		AnalyzedText analyze = analyze(string);
//
////		Predicate<Token> isSection = t -> t.getTokenType().equals(TokenType.SECTION_BEZEICHUNG);
////		Predicate<Token> isNorm = t -> t.getTokenType().equals(TokenType.NORM_BEZEICHNUNG);
////		Predicate<Token> isAktenzeichen = t -> t.getTokenType().equals(TokenType.AKTENZEICHEN_BEZEICHUNG);
////		Predicate<Token> isLink = t -> isSection.test(t) || isNorm.test(t) || isAktenzeichen.test(t);
//
//		String stringWithLinks = analyze.getTokens().stream().map(t -> {
//			if (isLink.test(t))
//				return "<a href=\"" + t.getNormalizedString() + "\">" + t.getString() + "</a>";
//			return t.getString();
//		}).collect(Collectors.joining(""));
//		return stringWithLinks;
//	}

	// private boolean whitelistInitialized = false;

	private AnalyzedText analyze(String query, int windowSize) {
		AnalyzedText text = new AnalyzedText(query);
		return analyze(text, windowSize);
	}

	private AnalyzedText analyze(AnalyzedText text, int windowSize) {
		List<Token> analyzedTokens = new ArrayList<>();
		for (int i = windowSize; i > 1; i--) {
			List<Token> window = window(text, i);
			for (Token token : window) {
				Optional<WhitelistEntry> whitelistEntryOption = analyzeToken(token);
				if (!whitelistEntryOption.isPresent())
					continue;

				WhitelistEntry whitelistEntry = whitelistEntryOption.get();
				text.remove(token.getBegin(), token.getEnd());
				analyzedTokens.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
						whitelistEntry.getValue(), whitelistEntry.getTokenType()));

			}
		}
		for (Token token : text.getTokens()) {
			Optional<WhitelistEntry> whitelistEntry = analyzeToken(token);
			if (whitelistEntry.isPresent())
				analyzedTokens.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
						whitelistEntry.get().getValue(), whitelistEntry.get().getTokenType()));
			else
				analyzedTokens.add(new Token(token.getBegin(), token.getEnd(), token.getString(),
						token.getNormalizedString(), token.getTokenType()));

		}

		return new AnalyzedText(analyzedTokens);
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

	private List<Token> window(AnalyzedText text, int maxWindowSize) {
		List<Token> results = new ArrayList<Token>();

		for (int i = 0; i < text.getSize() - maxWindowSize + 1; i++) {
			Token token = text.tokenWindow(i, maxWindowSize);
			results.add(token);
		}
		return results;
	}

	public static void main(String[] args) throws IOException {
		TextAnalyzer an = new TextAnalyzer();
		// an.addNormToWhitelist("BGB", "/gesetze/bgb");
//		an.addSectionToWhitelist("BGB", "§ 3", "", "/gesetze/bgb/3");
		// an.readInWhitelists();
		// an.analyzeToken(token)
		// Text analyze = an.analyze("Hello BGB §3 ii und so BGB ZPO id neu BGB
		// §15 ZPO.");
		AnalyzedText analyze = an.analyze("BGB Mensch");

		System.out.println(analyze);
	}

}
