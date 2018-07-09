package de.neosearch.analyzer;

public class WhitelistEntry {

	private String tokenType;
	private String value;

	public WhitelistEntry(String tokenType, String value) {
		this.tokenType = tokenType;
		this.value = value;
	}

	public String getTokenType() {
		return tokenType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		WhitelistEntry other = (WhitelistEntry) obj;
		if (tokenType != other.tokenType)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getValue() {
		return value;
	}
}
