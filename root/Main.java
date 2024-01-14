package root;

import expressions.Expression;

public class Main {
  public static void main(String[] args) {
    testParser();
  }

  public static void testParser() {
    String testString = "+-!~abcd + b - -b * c / d";
    System.out.println("input string: " + testString);
    System.out.println();

    Lexer lexer = new Lexer(testString);
    Parser parser = new BantamParser(lexer);

    Expression result = parser.parseExpression();
    StringBuilder builder = new StringBuilder();
    result.print(builder);
    String actual = builder.toString();
    System.out.println(actual);
  }

  public static void testLexer() {
    String testString = "a(b ? c : d, e + f)";
    System.out.println("input string: " + testString);
    System.out.println();

    Lexer lexer = new Lexer(testString);
    while (true) {
      Token t = lexer.next();
      if (t.getType() == TokenType.EOF) break;

      System.out.println(t.getType().name() + ' ' + t.getText());
    }
  }
}
