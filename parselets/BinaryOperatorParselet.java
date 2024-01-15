package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.OperatorExpression;

public class BinaryOperatorParselet implements InfixParselet {
  public BinaryOperatorParselet(int precedence, boolean isRight) {
    mPrecedence = precedence;
    mIsRight = isRight;
  }

  public Expression parse(Parser parser, Expression left, Token token) {
    Expression right = parser.parseExpression(
      mPrecedence - (mIsRight ? 1 : 0));

    return new OperatorExpression(left, token.getType(), right);
  }

  public int getPrecedence() {
    return mPrecedence;
  }

  private final int mPrecedence;
  private final boolean mIsRight;
}
