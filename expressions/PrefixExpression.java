package expressions;

import root.TokenType;

public class PrefixExpression implements Expression {
  public PrefixExpression(TokenType op, Expression expr) {
    mOp = op;
    mExpr = expr;
  }

  public void print(StringBuilder builder) {
    builder.append("(").append(mOp.punctuator());
    mExpr.print(builder);
    builder.append(")");
  }

  private final TokenType mOp;
  private final Expression mExpr;
}
