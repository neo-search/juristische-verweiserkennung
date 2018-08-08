package de.neosearch.verweiserkennung.tokenizer;

public class Token implements Comparable<Token> {
	private final int begin;
	private final int end;
//	private final String string;
	private final String normalizedString;

	private String tokenType;

	protected Token() {
//		string = null;
		normalizedString = null;
		begin = 0;
		end = 0;
	}

	public Token(int beginOfWord, int endOfWord, String normalizedString) {
		this.begin = beginOfWord;
		this.end = endOfWord;
//		this.string = string;
		this.normalizedString = normalizedString;
	}

	public Token(int beginOfWord, int endOfWord, String normalizedString, String tokenType) {
		this(beginOfWord, endOfWord, normalizedString);
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

//	public String getString() {
//		return string;
//	}

	public String getNormalizedString() {
		return normalizedString;
	}

	public String getTokenType() {
		return tokenType;
	}

	public boolean isWhitespace() {
		return TokenTypes.WHITESPACE.equals(tokenType);
	}

	public Token withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	@Override
	public String toString() {
		return "'" + normalizedString + "' (" + begin + ", " + end + ", " + tokenType + ")";
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
		result = prime * result + ((normalizedString == null) ? 0 : normalizedString.hashCode());
		result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
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
		if (normalizedString == null) {
			if (other.normalizedString != null)
				return false;
		} else if (!normalizedString.equals(other.normalizedString))
			return false;
		if (tokenType == null) {
			if (other.tokenType != null)
				return false;
		} else if (!tokenType.equals(other.tokenType))
			return false;
		return true;
	}

}
