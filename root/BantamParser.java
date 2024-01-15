package root;

import parselets.*;

public class BantamParser extends Parser {
  public BantamParser(Lexer lexer) {
    super(lexer);

    register(TokenType.NAME,       new NameParselet());
    register(TokenType.ASSIGN,     new AssignParselet());
    register(TokenType.QUESTION,   new ConditionalParselet());
    register(TokenType.LEFT_PAREN, new GroupParselet());
    register(TokenType.LEFT_PAREN, new CallParselet());

    prefix(TokenType.PLUS);
    prefix(TokenType.MINUS);
    prefix(TokenType.TILDE);
    prefix(TokenType.BANG);

    postfix(TokenType.BANG);

    infixLeft(TokenType.PLUS,     Precedence.SUM);
    infixLeft(TokenType.MINUS,    Precedence.SUM);
    infixLeft(TokenType.ASTERISK, Precedence.PRODUCT);
    infixLeft(TokenType.SLASH,    Precedence.PRODUCT);
    infixRight(TokenType.CARET,   Precedence.EXPONENT);
  }

  public void prefix(TokenType token) {
    register(token, new PrefixOperatorParselet(Precedence.PREFIX));
  }

  public void postfix(TokenType token) {
    register(token, new PostfixOperatorParselet(Precedence.POSTFIX));
  }

  public void infixLeft(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, false));
  }

  public void infixRight(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, true));
  }
}
