package root;

import parselets.*;

public class BantamParser extends Parser {
  public BantamParser(Lexer lexer) {
    super(lexer);

    register(TokenType.NAME, new NameParselet());

    prefix(TokenType.PLUS);
    prefix(TokenType.MINUS);
    prefix(TokenType.TILDE);
    prefix(TokenType.BANG);

    infixLeft(TokenType.PLUS,     Precedence.SUM);
    infixLeft(TokenType.MINUS,    Precedence.SUM);
    infixLeft(TokenType.ASTERISK, Precedence.PRODUCT);
    infixLeft(TokenType.SLASH,    Precedence.PRODUCT);
    infixRight(TokenType.CARET,   Precedence.EXPONENT);
  }

  public void prefix(TokenType token) {
    register(token, new PrefixOperatorParselet(Precedence.PREFIX));
  }

  public void infixLeft(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, false));
  }

  public void infixRight(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, true));
  }
}
