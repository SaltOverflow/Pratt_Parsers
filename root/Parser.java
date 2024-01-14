package root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import expressions.Expression;
import parselets.PrefixParselet;
import parselets.InfixParselet;

public class Parser {
  public Parser(Iterator<Token> tokens) {
    mTokens = tokens;
  }

  public void register(TokenType token, PrefixParselet parselet) {
    mPrefixParselets.put(token, parselet);
  }

  public void register(TokenType token, InfixParselet parselet) {
    mInfixParselets.put(token, parselet);
  }

  public Expression parseExpression() {
    Token token = consume();
    PrefixParselet prefix = mPrefixParselets.get(token.getType());

    if (prefix == null) throw new ParseException(
      "Could not parse \"" + token.getText() + "\".");

    Expression left = prefix.parse(this, token);

    token = consume();
    InfixParselet infix = mInfixParselets.get(token.getType());

    if (infix == null) return left;

    return infix.parse(this, left, token);
  }

  public Token consume() {
    return mTokens.next();
  }

  private final Iterator<Token> mTokens;
  private final Map<TokenType, PrefixParselet> mPrefixParselets =
    new HashMap<TokenType, PrefixParselet>();
  private final Map<TokenType, InfixParselet> mInfixParselets =
    new HashMap<TokenType, InfixParselet>();
}
