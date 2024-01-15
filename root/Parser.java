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

  public Expression parseExpression(int precedence) {
    Token token = consume();
    PrefixParselet prefix = mPrefixParselets.get(token.getType());

    if (prefix == null) throw new ParseException(
      "Could not parse \"" + token.getText() + "\".");

    Expression left = prefix.parse(this, token);

    while (precedence < getPrecedence()) {
      token = consume();

      InfixParselet infix = mInfixParselets.get(token.getType());
      left = infix.parse(this, left, token);
    }

    return left;
  }

  public Expression parseExpression() {
    return parseExpression(0);
  }

  public Token consume() {
    if (mRead == null) {
      return mTokens.next();
    } else {
      Token ret = mRead;
      mRead = null;
      return ret;
    }
  }

  private Token lookAhead() {
    if (mRead == null) {
      mRead = mTokens.next();
    }
    return mRead;
  }

  private int getPrecedence() {
    InfixParselet parser = mInfixParselets.get(lookAhead().getType());
    if (parser != null) return parser.getPrecedence();

    return 0;
  }

  private final Iterator<Token> mTokens;
  private Token mRead = null;
  private final Map<TokenType, PrefixParselet> mPrefixParselets =
    new HashMap<TokenType, PrefixParselet>();
  private final Map<TokenType, InfixParselet> mInfixParselets =
    new HashMap<TokenType, InfixParselet>();
}
