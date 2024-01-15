package root;

import expressions.Expression;

public class Main {
  public static void main(String[] args) {
    // Function calls
    test("a()", "a()");
    test("a(b)", "a(b)");
    test("a(b, c)", "a(b, c)");
    test("a(b)(c)", "a(b)(c)");
    test("a(b) + c(d)", "(a(b) + c(d))");
    test("a(b ? c : d, e + f)", "a((b ? c : d), (e + f))");

    // Unary precedence
    test("~!-+a", "(~(!(-(+a))))");
    test("a!!!", "(((a!)!)!)");

    // Unary and binary precedence
    test("-a * b", "((-a) * b)");
    test("!a + b", "((!a) + b)");
    test("~a ^ b", "((~a) ^ b)");
    test("-a!", "(-(a!))");
    test("!a!", "(!(a!))");

    // Binary precedence
    test("a = b + c * d ^ e - f / g", "(a = ((b + (c * (d ^ e))) - (f / g)))");

    // Binary associativity
    test("a = b = c", "(a = (b = c))");
    test("a + b - c", "((a + b) - c)");
    test("a * b / c", "((a * b) / c)");
    test("a ^ b ^ c", "(a ^ (b ^ c))");

    // Conditional operator
    test("a ? b : c ? d : e", "(a ? b : (c ? d : e))");
    test("a ? b ? c : d : e", "(a ? (b ? c : d) : e)");
    test("a + b ? c * d : e / f", "((a + b) ? (c * d) : (e / f))");

    // Grouping
    test("a + (b + c) + d", "((a + (b + c)) + d)");
    test("a ^ (b + c)", "(a ^ (b + c))");
    test("(!a)!", "((!a)!)");

    if (sFailed == 0) {
      System.out.println("Passed all " + sPassed + " tests.");
    } else {
      System.out.println("----");
      System.out.println("Failed " + sFailed + " out of " +
        (sFailed + sPassed) + " tests.");
    }
  }

  public static void test(String source, String expected) {
    Lexer lexer = new Lexer(source);
    Parser parser = new BantamParser(lexer);

    try {
      Expression result = parser.parseExpression();
      StringBuilder builder = new StringBuilder();
      result.print(builder);
      String actual = builder.toString();

      if (expected.equals(actual)) {
        sPassed++;
      } else {
        sFailed++;
        System.out.println("[FAIL] Expected: " + expected);
        System.out.println("         Actual: " + actual);
      }
    } catch(ParseException ex) {
      sFailed++;
      System.out.println("[FAIL] Expected: " + expected);
      System.out.println("          Error: " + ex.getMessage());
    }
  }

  private static int sPassed = 0;
  private static int sFailed = 0;

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
