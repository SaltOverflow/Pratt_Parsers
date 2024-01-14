package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.OperatorExpression;

public class BinaryOperatorParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    Expression right = parser.parseExpression();
    return new OperatorExpression(left, token.getType(), right);
  }
}
