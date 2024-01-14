package root;

public class Main {
  public static void main(String[] args) {
    testLexer();
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
