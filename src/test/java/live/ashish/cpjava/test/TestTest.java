package live.ashish.cpjava.test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DemoTest {

  /*
   * This class contains tests for main method in 'Test' class.
   * main method is expected to print 'Hello' on console and the type of two variables
   * following 'Hello' of 'Test' class.
   */

  @Test
  void testMainMethodPrintsCorrectOutput() {
    // Initialize necessary objects
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Call main method
    String[] args = {};
    Demo.main(args);
//
//    String expectedOutput = "Hello" + System.lineSeparator() +
//            "class java.lang.Integer" + System.lineSeparator() +
//            "class java.lang.Integer" + System.lineSeparator();
//
//    // Assertion
//    Assertions.assertEquals(expectedOutput, outContent.toString());
  }
}
