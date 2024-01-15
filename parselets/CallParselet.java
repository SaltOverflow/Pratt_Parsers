package parselets;

import java.util.ArrayList;
import java.util.List;

import root.Parser;
import root.Precedence;
import root.Token;
import root.TokenType;
import expressions.CallExpression;
import expressions.Expression;

public class CallParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    List<Expression> args = new ArrayList<Expression>();
    if (!parser.match(TokenType.RIGHT_PAREN)) {
      do {
        args.add(parser.parseExpression());
      } while (parser.match(TokenType.COMMA));
      parser.consume(TokenType.RIGHT_PAREN);
    }

    return new CallExpression(left, args);
  }

  public int getPrecedence() {
    return Precedence.CALL;
  }
}
