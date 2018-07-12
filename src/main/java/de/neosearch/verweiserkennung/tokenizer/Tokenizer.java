package de.neosearch.verweiserkennung.tokenizer;

import java.util.List;

public interface Tokenizer {

	List<String> tokenize(String text);

}
