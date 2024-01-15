package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.PostfixExpression;

public class PostfixOperatorParselet implements InfixParselet {
  public PostfixOperatorParselet(int precedence) {
    mPrecedence = precedence;
  }

  public Expression parse(Parser parser, Expression left, Token token) {
    return new PostfixExpression(left, token.getType());
  }

  public int getPrecedence() {
    return mPrecedence;
  }

  private final int mPrecedence;
}
