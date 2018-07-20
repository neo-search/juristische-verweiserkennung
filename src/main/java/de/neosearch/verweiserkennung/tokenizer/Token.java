package de.neosearch.verweiserkennung.tokenizer;

public class Token implements Comparable<Token> {
	private int begin = 0;
	private int end = 0;
	private String string = "";
	private String normalizedString = "";

	private String tokenType;

	protected Token() {

	}

	public Token(int beginOfWord, int endOfWord, String string, String normalizedString) {
		this.begin = beginOfWord;
		this.end = endOfWord;
		this.string = string;
		this.normalizedString = normalizedString;
	}

	public Token(int beginOfWord, int endOfWord, String actualString, String normalizedString, String tokenType) {
		this(beginOfWord, endOfWord, actualString, normalizedString);
		this.tokenType = tokenType;
	}

	public boolean stringEquals(String str) {
		return normalizedString.equals(str);
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public String getString() {
		return string;
	}

	public String getNormalizedString() {
		return normalizedString;
	}

	public String getTokenType() {
		return tokenType;
	}

	public Token withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	@Override
	public String toString() {
//		if (tokenType.equals(AnalyzedText.WHITESPACE_TOKENTYPE))
//			return "'\\w'-> (" + begin + ", " + end + ", " + tokenType + ")";
		return "'" + string + ":" + normalizedString + "' (" + begin + ", " + end + ", " + tokenType + ")";
	}

	@Override
	public int compareTo(Token o) {
		return begin >= o.begin ? 1 : begin <= o.begin ? -1 : 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + begin;
		result = prime * result + end;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (begin != other.begin)
			return false;
		if (end != other.end)
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

}
