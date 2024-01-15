package parselets;

import root.Parser;
import root.Token;
import root.TokenType;
import expressions.Expression;

public class GroupParselet implements PrefixParselet {
  public Expression parse(Parser parser, Token token) {
    Expression expr = parser.parseExpression();
    parser.consume(TokenType.RIGHT_PAREN);

    return expr;
  }
}
