package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;
import expressions.NameExpression;

public class NameParselet implements PrefixParselet {
  public Expression parse(Parser parse, Token token) {
    return new NameExpression(token.getText());
  }
}
