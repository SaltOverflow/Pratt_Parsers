package expressions;

public class ConditionalExpression implements Expression {
  public ConditionalExpression(Expression cond, Expression truePath, Expression falsePath) {
    mCond = cond;
    mTruePath = truePath;
    mFalsePath = falsePath;
  }

  public void print(StringBuilder builder) {
    builder.append("(");
    mCond.print(builder);
    builder.append(" ? ");
    mTruePath.print(builder);
    builder.append(" : ");
    mFalsePath.print(builder);
    builder.append(")");
  }

  private final Expression mCond;
  private final Expression mTruePath;
  private final Expression mFalsePath;
}
