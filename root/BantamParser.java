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

    infix(TokenType.PLUS);
    infix(TokenType.MINUS);
    infix(TokenType.ASTERISK);
    infix(TokenType.SLASH);
    infix(TokenType.CARET);
  }

  public void prefix(TokenType token) {
    register(token, new PrefixOperatorParselet());
  }

  public void infix(TokenType token) {
    register(token, new BinaryOperatorParselet());
  }
}
