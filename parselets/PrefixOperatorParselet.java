package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.PrefixExpression;

public class PrefixOperatorParselet implements PrefixParselet {
  public Expression parse(Parser parser, Token token) {
    Expression expr = parser.parseExpression();
    return new PrefixExpression(token.getType(), expr);
  }
}
