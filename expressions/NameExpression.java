package expressions;

public class NameExpression implements Expression {
  public NameExpression(String name) {
    mName = name;
  }

  public String getName() { return mName; }

  public void print(StringBuilder builder) {
    builder.append(mName);
  }

  private final String mName;
}
