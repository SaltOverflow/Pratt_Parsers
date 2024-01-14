package expressions;

import root.TokenType;

public class OperatorExpression implements Expression {
  public OperatorExpression(Expression left, TokenType op, Expression right) {
    mLeft = left;
    mOp = op;
    mRight = right;
  }

  public void print(StringBuilder builder) {
    builder.append("(");
    mLeft.print(builder);
    builder.append(" ").append(mOp.punctuator()).append(" ");
    mRight.print(builder);
    builder.append(")");
  }

  private final Expression mLeft;
  private final TokenType mOp;
  private final Expression mRight;
}
