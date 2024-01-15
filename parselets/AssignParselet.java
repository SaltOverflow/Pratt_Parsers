package parselets;

import root.ParseException;
import root.Parser;
import root.Precedence;
import root.Token;
import expressions.AssignExpression;
import expressions.Expression;
import expressions.NameExpression;

public class AssignParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    if (!(left instanceof NameExpression)) throw new ParseException(
      "The left-hand side of an assignment must be a name.");
    String name = ((NameExpression)left).getName();

    Expression right = parser.parseExpression(Precedence.ASSIGNMENT - 1);
    return new AssignExpression(name, right);
  }

  public int getPrecedence() { return Precedence.ASSIGNMENT; }
}
