package parselets;

import root.Parser;
import root.Token;
import expressions.Expression;

public interface PrefixParselet {
  Expression parse(Parser parser, Token token);
}
