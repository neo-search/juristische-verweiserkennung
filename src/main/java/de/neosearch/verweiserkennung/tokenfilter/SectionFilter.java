package de.neosearch.verweiserkennung.tokenfilter;

import java.util.List;
import java.util.regex.Pattern;

import de.neosearch.verweiserkennung.tokenizer.Token;

public class SectionFilter extends LowercaseWhitelistFilter {
	// private static final String CLEAN_SECTIONS = "( abs\\. \\d+"//
	// + "\\| satz \\d+" //
	// + "\\| s \\d+"//
	// + "\\| nr \\d+" //
	// + "\\| nr\\. \\d+"//
	// + "\\| alt \\d+" //
	// + "\\|  buchst \\w" //
	// + "|  vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d)";
	private static final Pattern CLEAN_SECTIONS = Pattern.compile("( abs\\. \\d+"//
			+ "| nr. \\d+" //
			+ "| satz \\d+" //
			+ "| s \\d+"//
			+ "| nr \\d+" //
			+ "| alt \\d+" //
			+ "| buchst \\w" //
			+ "|," //
			+ "| \\d.+ aufl." //
			+ "| vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d" //
//			+ "|\u00A0" //  	NO-BREAK SPACE
			+ ")");

	public SectionFilter(String tokenType, List<String> token, List<String> normalizedToken) {
		super(tokenType, token, normalizedToken);
	}

	public Token accept(Token token) {
		String tokenString = removeAnchorInformation(token.getNormalizedString());
		if (whitelistContains(tokenString))
			return new Token(token.getBegin(), token.getEnd(), token.getString(), getNormalized(tokenString),
					this.tokenType);
		return null;
	};

	public static String removeAnchorInformation(String normAbkuerzung) {
//		if (normAbkuerzung.startsWith("tkg, ") && normAbkuerzung.endsWith("45i"))
//			System.out.println("GO2");
		// if (normAbkuerzung.equalsIgnoreCase("§ 547 Nr. 4 ZPO"))
		// System.out.println("GO!" + normAbkuerzung);
		return CLEAN_SECTIONS.matcher(normAbkuerzung).replaceAll("");
		// return normAbkuerzung //
		// .replaceAll(" abs\\. \\d+", "")//
		// .replaceAll(" satz \\d+", "")//
		// .replaceAll(" s \\d+", "")//
		// .replaceAll(" nr \\d+", "")//
		// .replaceAll(" nr\\. \\d+", "")//
		// .replaceAll(" alt \\d+", "")//
		// .replaceAll(" buchst \\w", "")//
		// .replaceAll(" vom \\d\\d\\.\\d\\d\\.\\d\\d\\d\\d", "");
	}

	public static void main(String[] args) {
		String trs = "entsprechender anwendung von § 547 nr. 4 zpo";

		// String replaceAll = trs.replaceAll(CLEAN_SECTIONS, "");
		// System.out.println(replaceAll);
	}
}
