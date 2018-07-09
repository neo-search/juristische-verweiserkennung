package de.neosearch.analyzer;

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

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getString() {
		return string;
	}

	public String getNormalizedString() {
		return normalizedString;
	}

	public void setString(String string) {
		this.string = string;
		this.normalizedString = string.toLowerCase();
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		if (tokenType.equals(AnalyzedText.WHITESPACE_TOKENTYPE))
			return "'\\w'-> (" + begin + ", " + end + ", " + tokenType + ")";
		return "'" + string + "'->'" + normalizedString + "' (" + begin + ", " + end + ", " + tokenType + ")";
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
