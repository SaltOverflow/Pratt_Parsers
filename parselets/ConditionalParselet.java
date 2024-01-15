package parselets;

import root.Parser;
import root.Precedence;
import root.Token;
import root.TokenType;
import expressions.ConditionalExpression;
import expressions.Expression;

public class ConditionalParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    Expression truePath = parser.parseExpression(Precedence.CONDITIONAL - 1);
    parser.consume(TokenType.COLON);
    Expression falsePath = parser.parseExpression(Precedence.CONDITIONAL - 1);

    return new ConditionalExpression(left, truePath, falsePath);
  }

  public int getPrecedence() {
    return Precedence.CONDITIONAL;
  }
}
