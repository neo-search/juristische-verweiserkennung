package de.neosearch.verweiserkennung.tokenfilter;

import java.util.List;
import java.util.regex.Pattern;

import de.neosearch.verweiserkennung.tokenizer.Token;

public class SectionFilter extends LowercaseWhitelistFilter {
	private static final Pattern CLEAN_SECTIONS = Pattern.compile("( abs\\. \\d+"//
			+ "| nr. \\d+" //
			+ "| satz \\d+" //
			+ "| s \\d+"//
			+ "| nr \\d+" //
			+ "| alt \\d+" //
			+ "| fall \\d+" //
			+ "| buchst \\w" //
			+ "|," //
			+ "| \\d.+ aufl." //
			+ "| vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d" //
			+ ")");

	public SectionFilter(String tokenType, List<String> token, List<String> normalizedToken) {
		super(tokenType, token, normalizedToken);
	}

	public Token acceptImpl(Token token) {
		String tokenString = removeAnchorInformation(token.getNormalizedString());
		if (whitelistContains(tokenString))
			return new Token(token.getBegin(), token.getEnd(), getNormalized(tokenString), this.tokenType);
		return null;
	};

	public static String removeAnchorInformation(String normAbkuerzung) {
		return CLEAN_SECTIONS.matcher(normAbkuerzung).replaceAll("");
	}

	public static void main(String[] args) {
//		String trs = "entsprechender anwendung von § 547 nr. 4 zpo";
//		String bla ="Die Rechtsbeschwerde an das Bundesarbeitsgericht findet gemäß § 78 Satz 1 ArbGG, § 574 Abs. 1 Satz 1 ZPO nur statt, wenn dies im Gesetz ausdrücklich bestimmt ist (Nr. 1) oder das Beschwerdegericht sie in dem Beschluss ";

		// String replaceAll = trs.replaceAll(CLEAN_SECTIONS, "");
		// System.out.println(replaceAll);
	}
}
