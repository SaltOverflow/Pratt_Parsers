package root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import expressions.Expression;
import parselets.PrefixParselet;

public class Parser {
  public Parser(Iterator<Token> tokens) {
    mTokens = tokens;
  }

  public void register(TokenType token, PrefixParselet parselet) {
    mPrefixParselets.put(token, parselet);
  }

  public Expression parseExpression() {
    Token token = consume();
    PrefixParselet prefix = mPrefixParselets.get(token.getType());

    if (prefix == null) throw new ParseException(
      "Could not parse \"" + token.getText() + "\".");

    return prefix.parse(this, token);
  }

  public Token consume() {
    return mTokens.next();
  }

  private final Iterator<Token> mTokens;
  private final Map<TokenType, PrefixParselet> mPrefixParselets =
    new HashMap<TokenType, PrefixParselet>();
}
