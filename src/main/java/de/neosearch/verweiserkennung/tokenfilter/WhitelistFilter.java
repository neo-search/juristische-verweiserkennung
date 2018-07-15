package de.neosearch.verweiserkennung.tokenfilter;

import de.neosearch.verweiserkennung.tokenizer.Token;

public abstract class WhitelistFilter {

	public abstract Token accept(Token token);

}
