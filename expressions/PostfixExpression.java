package expressions;

import root.TokenType;

public class PostfixExpression implements Expression {
  public PostfixExpression(Expression expr, TokenType op) {
    mExpr = expr;
    mOp = op;
  }

  public void print(StringBuilder builder) {
    builder.append("(");
    mExpr.print(builder);
    builder.append(mOp.punctuator()).append(")");
  }

  private final Expression mExpr;
  private final TokenType mOp;
}
