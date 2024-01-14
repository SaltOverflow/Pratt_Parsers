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
  }

  public void prefix(TokenType token) {
    register(token, new PrefixOperatorParselet());
  }
}
