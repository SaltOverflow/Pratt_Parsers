package root;

public final class Token {
  public Token(TokenType type, String text) {
    mType = type;
    mText = text;
  }

  public TokenType getType() { return mType; }
  public String getText() { return mText; }

  @Override
  public String toString() { return mText; }

  private final TokenType mType;
  private final String mText;
}
