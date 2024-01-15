package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.PrefixExpression;

public class PrefixOperatorParselet implements PrefixParselet {
  public PrefixOperatorParselet(int precedence) {
    mPrecedence = precedence;
  }

  public Expression parse(Parser parser, Token token) {
    Expression expr = parser.parseExpression(mPrecedence);
    return new PrefixExpression(token.getType(), expr);
  }

  private final int mPrecedence;
}
