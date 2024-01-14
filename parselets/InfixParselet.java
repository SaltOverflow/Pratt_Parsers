package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;

// This is "anything but prefix" (ie. includes postfix, ?:)
public interface InfixParselet {
  Expression parse(Parser parser, Expression left, Token token);
}
