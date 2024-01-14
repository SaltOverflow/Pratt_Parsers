package root;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Lexer implements Iterator<Token> {
  public Lexer(String text) {
    mIndex = 0;
    mText = text;

    for (TokenType type : TokenType.values()) {
      Character punctuator = type.punctuator();
      if (punctuator != null) {
        mPunctuators.put(punctuator, type);
      }
    }
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Token next() {
    while (mIndex < mText.length()) {
      char c = mText.charAt(mIndex++);

      if (mPunctuators.containsKey(c)) {
        return new Token(mPunctuators.get(c), Character.toString(c));
      } else if (Character.isLetter(c)) {
        int start = mIndex - 1;
        while (mIndex < mText.length()) {
          if (!Character.isLetter(mText.charAt(mIndex))) break;
          mIndex++;
        }

        String name = mText.substring(start, mIndex);
        return new Token(TokenType.NAME, name);
      } else {
        // ignore
      }
    }

    // return EOF tokens when out of text to parse
    return new Token(TokenType.EOF, "");
  }

  private final Map<Character, TokenType> mPunctuators =
    new HashMap<Character, TokenType>();
  private final String mText;
  private int mIndex = 0;
}
